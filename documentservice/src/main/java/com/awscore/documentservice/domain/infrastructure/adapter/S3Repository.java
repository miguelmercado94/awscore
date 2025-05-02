package com.awscore.documentservice.domain.infrastructure.adapter;

import com.awscore.documentservice.domain.model.FileS3;
import reactor.core.publisher.Mono;

public interface S3Repository {
    Mono<String> uploadFile(FileS3 fileS3, String bucketName);
    Mono<FileS3> downloadFile(String key, String bucketName);
}
