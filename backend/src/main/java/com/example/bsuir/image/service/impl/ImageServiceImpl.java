package com.example.bsuir.image.service.impl;

import com.example.bsuir.image.model.Image;
import com.example.bsuir.image.repository.ImageRepository;
import com.example.bsuir.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image getById(Long id) {
        return imageRepository.findByIdOrThrow(id);
    }

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }
}
