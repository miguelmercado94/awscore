package com.awscore.autenticacionservice.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String numIdentification;
    private String email;
    private String password;
    private String fullPhone;
    private boolean active;
    private AuthDevice device;
}
