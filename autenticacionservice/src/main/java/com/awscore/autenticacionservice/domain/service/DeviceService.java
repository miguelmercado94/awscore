package com.awscore.autenticacionservice.domain.service;

import com.awscore.autenticacionservice.domain.model.Device;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DeviceService {

    Mono<Device> findById(long id);
    Mono<Device> save(Device device);
    Flux<Device> findAllByUser(long idUser);

}
