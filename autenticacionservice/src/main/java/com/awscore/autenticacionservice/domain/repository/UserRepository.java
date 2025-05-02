package com.awscore.autenticacionservice.domain.repository;

import com.awscore.autenticacionservice.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> saveUser(User user);
    Mono<User> findByEmail(String email);

}
