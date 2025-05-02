package com.awscore.documentservice.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FileResponse {

    private String key;
    
    private String message;
}
