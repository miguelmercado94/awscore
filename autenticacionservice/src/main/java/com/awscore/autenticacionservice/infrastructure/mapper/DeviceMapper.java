package com.awscore.autenticacionservice.infrastructure.mapper;

import com.awscore.autenticacionservice.domain.model.Device;
import com.awscore.autenticacionservice.infrastructure.entity.DeviceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})  
public interface DeviceMapper {

    @Mapping(source = "userId", target = "user.id") 
    Device toDto(DeviceEntity entity);

    @Mapping(source = "user.id", target = "userId")
    DeviceEntity toEntity(Device dto);
}
