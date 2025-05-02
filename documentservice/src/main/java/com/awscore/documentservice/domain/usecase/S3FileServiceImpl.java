package com.awscore.documentservice.domain.usecase;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.awscore.documentservice.controller.request.FileRequest;
import com.awscore.documentservice.controller.response.FileResponse;
import com.awscore.documentservice.domain.infrastructure.adapter.S3Adapter;
import com.awscore.documentservice.domain.model.FileS3;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class S3FileServiceImpl implements S3FileService {

    @Value("${s3.bucket-name}")
    private String bucketNameDefault;
    
    private final S3Adapter s3Repo;

    @Override
    public Mono<FileResponse> uploadFile(FileRequest request) {
        byte[] dataByte = Base64.getDecoder().decode(request.getDataB64());

        FileS3 fileS3 = FileS3.builder()
                .fileFullName(request.getFileName()+"."+request.getTypeFile().toString())
                .locationURL(request.getUrlFile())
                .dataByte(dataByte)
                .build();

        String buckeName = request.getBucketName();
        
        return s3Repo.uploadFile(fileS3, buckeName  == null || buckeName.isBlank() ? bucketNameDefault :request.getBucketName())
                .map(key -> new FileResponse(key, "File uploaded successfully"));
    }
}
