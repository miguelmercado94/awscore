package com.awscore.autenticacionservice.infrastructure.mapper;

import com.awscore.autenticacionservice.domain.model.Token;
import com.awscore.autenticacionservice.infrastructure.entity.TokenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, DeviceMapper.class})
public interface TokenMapper {

    TokenMapper INSTANCE = Mappers.getMapper(TokenMapper.class);

    @Mapping(source = "userId", target = "user.id")  
    @Mapping(source = "deviceId", target = "device.id")  
    Token toDto(TokenEntity entity);

    @Mapping(source = "user.id", target = "userId") 
    @Mapping(source = "device.id", target = "deviceId") 
    TokenEntity toEntity(Token dto);
}


