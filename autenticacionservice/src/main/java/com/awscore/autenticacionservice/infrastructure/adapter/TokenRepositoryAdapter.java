package com.awscore.autenticacionservice.infrastructure.adapter;

import com.awscore.autenticacionservice.domain.model.Token;
import com.awscore.autenticacionservice.domain.repository.TokenRepository;
import com.awscore.autenticacionservice.infrastructure.entity.TokenEntity;
import com.awscore.autenticacionservice.infrastructure.mapper.TokenMapper;
import com.awscore.autenticacionservice.infrastructure.repository.ETokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TokenRepositoryAdapter implements TokenRepository {

    private final ETokenRepository eTokenRepository;
    private final TokenMapper mapper = TokenMapper.INSTANCE;

    @Override
    public Mono<Token> save(Token token) {
        TokenEntity entity = mapper.toEntity(token);
        return eTokenRepository.save(entity)
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> saveAll(List<Token> tokens) {
        List<TokenEntity> entities = tokens.stream()
                .map(mapper::toEntity)
                .toList();

        return Flux.fromIterable(entities)
                .flatMap(eTokenRepository::save)
                .then(); // returns Mono<Void>
    }
}
