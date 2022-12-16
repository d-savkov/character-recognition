package com.example.bsuir.image.service;

import com.example.bsuir.image.dto.request.CreateImageRequest;
import com.example.bsuir.image.model.Image;
import com.example.bsuir.shared.service.Service;

import java.util.List;

public interface ImageService extends Service<Image, Long> {
    List<Image> getAllByCharacterId(Long characterId);

    Image create(CreateImageRequest request);

    void deleteById(Long id);
}
