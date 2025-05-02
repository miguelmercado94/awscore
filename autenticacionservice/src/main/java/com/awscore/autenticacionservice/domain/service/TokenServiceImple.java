package com.awscore.autenticacionservice.domain.service;

import com.awscore.autenticacionservice.domain.model.Token;
import com.awscore.autenticacionservice.domain.model.User;
import com.awscore.autenticacionservice.domain.repository.TokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenServiceImple implements TokenService{

    @Value("${application.security.jwt.secret-key}")
    private String secrectKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long jwtRefreshExpiration;

    private final TokenRepository tokenRepository;

    @Override
    public Flux<Token> findAllByUser(long idUser) {

        return null;
    }

    @Override
    public Mono<Tuple2<Token, Token>> generateTokensAndSave(User user) {
        Mono<Token> accessTokenMono = generateToken(user,jwtExpiration);
        Mono<Token> refreshTokenMono = generateToken(user,jwtRefreshExpiration);

        return Mono.zip(accessTokenMono, refreshTokenMono)
                .flatMap(tuple -> {
                    Token access = tuple.getT1();
                    Token refresh = tuple.getT2();
                    return tokenRepository.saveAll(List.of(access, refresh))
                            .thenReturn(Tuples.of(access, refresh));
                });
    }

    private Mono<Token> generateToken(User user, long jwtExpiration) {
        var token = Token.builder()
                .token(buidToken(user,jwtExpiration))
                .expired(false)
                .revoked(false)
                .user(user)
                .device(user.getDevice())
                .build();
        return Mono.just(token);
    }



    @Override
    public Flux<Token> findByUserAndRevokedAndExpired(long idUser, boolean revoked, boolean expired) {
        return null;
    }

    private String buidToken(final User user,final long expiration){
        long currentDate =  System.currentTimeMillis();
        Date current = new Date (currentDate);
        Date expirationDate =new Date(currentDate +expiration);

        return Jwts.builder()
                .setId(user.getNumIdentification())
                .addClaims(Map.of("fullPhone",user.getFullPhone()))
                .setSubject(user.getEmail())
                .setIssuedAt(current)
                .setExpiration(expirationDate)
                .signWith(getSingInKey())
                .compact();
    }

    private SecretKey getSingInKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secrectKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
