package com.awscore.autenticacionservice.domain.service;

import com.awscore.autenticacionservice.domain.model.Token;
import com.awscore.autenticacionservice.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface TokenService {

    Flux<Token> findAllByUser(long idUser);
    public Mono<Tuple2<Token, Token>> generateTokensAndSave(User user);
    Flux<Token> findByUserAndRevokedAndExpired(long idUser, boolean revoked, boolean expired);

}
