package com.awscore.autenticacionservice.domain.service;

import com.awscore.autenticacionservice.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> findByNumIdentification(String numIdentification);
    Mono<User> findById(long id);
    Mono<User> save(User user);
}
