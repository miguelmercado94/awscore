package com.awscore.autenticacionservice.domain.service;

import com.awscore.autenticacionservice.domain.model.User;
import com.awscore.autenticacionservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImple implements UserService{

    private final UserRepository userRepository;

    @Override
    public Mono<User> findByNumIdentification(String numIdentification) {
        return null;
    }

    @Override
    public Mono<User> findById(long id) {
        return null;
    }

    @Override
    public Mono<User> save(User user) {
        return userRepository.saveUser(user);
    }
}
