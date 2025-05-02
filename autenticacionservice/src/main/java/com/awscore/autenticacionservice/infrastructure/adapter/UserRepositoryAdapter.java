package com.awscore.autenticacionservice.infrastructure.adapter;

import com.awscore.autenticacionservice.domain.model.User;
import com.awscore.autenticacionservice.domain.repository.UserRepository;
import com.awscore.autenticacionservice.infrastructure.mapper.UserMapper;
import com.awscore.autenticacionservice.infrastructure.repository.EUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final EUserRepository eUserRepository;
    private final UserMapper userMapper;

    @Override
    public Mono<User> saveUser(User user) {
        return eUserRepository.save(userMapper.toEntity(user))
                .map(userMapper::toDto);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return eUserRepository.findByEmail(email)
                .map(userMapper::toDto);
    }
}
