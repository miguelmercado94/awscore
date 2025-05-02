package com.awscore.autenticacionservice.controller.response;

import com.awscore.autenticacionservice.domain.model.Token;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponse {

    private  String accessToken;

    private  String refreshToken;

    private  String userEmail;


    public static AuthResponse fromDomain(Token accessToken, Token refreshToken) {
        AuthResponse response = new AuthResponse();
        response.setAccessToken(accessToken.getToken());
        response.setRefreshToken(refreshToken.getToken());
        response.setUserEmail(accessToken.getUser().getEmail());
        return response;
    }
}
