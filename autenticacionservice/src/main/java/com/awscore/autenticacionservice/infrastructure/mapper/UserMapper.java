package com.awscore.autenticacionservice.infrastructure.mapper;

import com.awscore.autenticacionservice.domain.model.User;
import com.awscore.autenticacionservice.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DeviceMapper.class})
public interface UserMapper {

    UserEntity toEntity(User dto);

    @Mapping(target = "device", ignore = true) // Ignorar la propiedad device
    User toDto(UserEntity entity);
}
