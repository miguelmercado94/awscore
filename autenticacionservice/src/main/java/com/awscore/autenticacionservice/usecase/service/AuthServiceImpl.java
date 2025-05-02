package com.awscore.autenticacionservice.usecase.service;

import com.awscore.autenticacionservice.controller.request.AuthRequest;
import com.awscore.autenticacionservice.controller.response.AuthResponse;
import com.awscore.autenticacionservice.domain.model.Token;
import com.awscore.autenticacionservice.domain.model.User;
import com.awscore.autenticacionservice.domain.service.SqsService;
import com.awscore.autenticacionservice.domain.service.TokenService;
import com.awscore.autenticacionservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserService userService;

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;

    private final SqsService sqsService;

    /*@Override
    public Mono<AuthResponse> register(AuthRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .active(request.isActive())
                .fullPhone(request.getFullPhone())
                .numIdentification(request.getNumIdentification())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return userService.save(user)
                .flatMap(savedUser ->
                        tokenService.generateTokensAndSave(savedUser)
                                .map(tuple -> AuthResponse.fromDomain(tuple.getT1(), tuple.getT2()))
                );
    }*/

    @Override
    public Mono<AuthResponse> register(AuthRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .active(request.isActive())
                .fullPhone(request.getFullPhone())
                .numIdentification(request.getNumIdentification())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return userService.save(user)
                .flatMap(savedUser -> 
                    tokenService.generateTokensAndSave(savedUser)
                        .map(tuple -> {
                            AuthResponse authResponse = AuthResponse.fromDomain(tuple.getT1(), tuple.getT2());
                            String message = String.format("{\"email\":\"%s\", \"status\":\"registered\"}", savedUser.getEmail());
                            sqsService.sendMessage(message);
                            
                            return authResponse;
                        })
                );
    }
    @Override
    public Mono<AuthResponse> login(AuthRequest request) {
        return null;
    }

    @Override
    public Mono<AuthResponse> logout(Token token) {
        return null;
    }

    @Override
    public Mono<AuthResponse> refreshToken(Token token) {
        return null;
    }

    @Override
    public Mono<Boolean> validateToken(String token) {
        return null;
    }


}
