package com.awscore.autenticacionservice.domain.repository;

import com.awscore.autenticacionservice.domain.model.Token;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TokenRepository {

    Mono<Token> save(Token token);
    Mono<Void> saveAll(List<Token> tokens);

}
