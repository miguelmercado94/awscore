package com.awscore.autenticacionservice.usecase.service;

import com.awscore.autenticacionservice.controller.request.AuthRequest;
import com.awscore.autenticacionservice.controller.response.AuthResponse;
import com.awscore.autenticacionservice.domain.model.Token;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<AuthResponse>  register(AuthRequest request);
    Mono<AuthResponse> login(AuthRequest request);
    Mono<AuthResponse> logout(Token token);
    Mono<AuthResponse> refreshToken(Token token);
    Mono<Boolean> validateToken(String token); // 👈 nuevo método


}
