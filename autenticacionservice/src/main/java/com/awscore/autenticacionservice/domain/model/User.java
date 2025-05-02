package com.awscore.autenticacionservice.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

    private Long id;
    private String numIdentification;
    private String email;
    private String password;
    private String fullPhone;
    private boolean active;
    private Device device;
}
