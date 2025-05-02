package com.awscore.documentservice.domain.usecase;

import com.awscore.documentservice.controller.request.FileRequest;
import com.awscore.documentservice.controller.response.FileResponse;

import reactor.core.publisher.Mono;

public interface S3FileService {

	
	public Mono<FileResponse> uploadFile(FileRequest request);
	
}
