package com.awscore.autenticacionservice.controller;

import com.awscore.autenticacionservice.controller.request.AuthRequest;
import com.awscore.autenticacionservice.controller.response.AuthResponse;
import com.awscore.autenticacionservice.domain.model.Token;
import com.awscore.autenticacionservice.usecase.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
   

    @PostMapping("/register")
    public Mono<AuthResponse> register(@RequestBody AuthRequest request) {
        return authService.register(request);
    }
    @PostMapping("/login")
    public Mono<AuthResponse> login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/logout")
    public Mono<AuthResponse> logout(@RequestBody Token token) {
        return authService.logout(token);
    }

    @GetMapping("/validate")
    public Mono<Boolean> validateToken(@RequestHeader("Authorization") String bearerToken) {
        String token = extractToken(bearerToken);
        return authService.validateToken(token);
    }

    private String extractToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return bearerToken;
    }
}
