package com.awscore.autenticacionservice.infrastructure.repository;

import com.awscore.autenticacionservice.infrastructure.entity.TokenEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ETokenRepository extends ReactiveCrudRepository<TokenEntity, Long> {
}
