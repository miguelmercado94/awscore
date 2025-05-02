package com.awscore.documentservice.controller;

import com.awscore.documentservice.controller.request.FileRequest;
import com.awscore.documentservice.controller.response.FileResponse;
import com.awscore.documentservice.domain.usecase.S3FileService;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class FileUploadController {

    private final S3FileService s3FileService;

    @PostMapping
    public Mono<FileResponse> uploadFile(@RequestBody FileRequest request) {
        System.out.println(request.getDataB64());
        return s3FileService.uploadFile(request);
    }
}
