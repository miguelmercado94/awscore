package com.awscore.autenticacionservice.usecase.service;

import org.springframework.stereotype.Service;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;

@Service
public class TestDatabaseService {

    private final DatabaseClient databaseClient;

    public TestDatabaseService(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public Mono<String> testConnection() {
        return databaseClient.sql("SELECT current_timestamp")
                .map((row, metadata) -> row.get("current_timestamp", String.class))
                .one();
    }
}

