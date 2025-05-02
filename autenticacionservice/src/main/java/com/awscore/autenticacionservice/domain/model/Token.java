package com.awscore.autenticacionservice.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Token {

    public enum TokenType {
        BEARER
    }

    private Long id;
    private String token;
    private final TokenType tokenType = TokenType.BEARER;
    private boolean revoked;
    private boolean expired;
    private User user;
    private Device device;
}
