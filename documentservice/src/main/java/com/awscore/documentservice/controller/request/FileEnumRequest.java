package com.awscore.documentservice.controller.request;

public enum FileEnumRequest {

    JPG, PNG, JPEG;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
