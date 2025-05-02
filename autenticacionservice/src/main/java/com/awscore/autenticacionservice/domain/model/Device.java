package com.awscore.autenticacionservice.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Device {

    private Long id;
    private String ip;
    private String description;
    private User user;
}
