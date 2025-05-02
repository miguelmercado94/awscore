package com.awscore.autenticacionservice.controller.request;

import com.awscore.autenticacionservice.domain.model.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthDevice {
    private String ip;
    private String Description;
    private User user;
}
