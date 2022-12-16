package com.example.bsuir.image.service;

import com.example.bsuir.image.dto.response.ImagePresignResponse;

public interface ImagePresignService {

    ImagePresignResponse getPresignResponse(Long showId, Long characterId, String fileType);
}
