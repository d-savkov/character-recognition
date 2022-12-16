package com.example.bsuir.image.service.impl;

import com.example.bsuir.image.dto.request.CreateImageRequest;
import com.example.bsuir.image.dto.request.mapper.CreateImageMapper;
import com.example.bsuir.image.model.Image;
import com.example.bsuir.image.repository.ImageRepository;
import com.example.bsuir.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;
    private final CreateImageMapper createMapper;

    @Override
    public Image getById(Long id) {
        return repository.findByIdOrThrow(id);
    }

    @Override
    public List<Image> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Image> getAllByCharacterId(Long characterId) {
        return repository.findAllByCharacterId(characterId);
    }

    @Override
    public Image create(CreateImageRequest request) {
        return repository.save(createMapper.toEntity(request));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
