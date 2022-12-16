package com.example.bsuir.image.dto.request.mapper;

import com.example.bsuir.character.repository.CharacterRepository;
import com.example.bsuir.image.dto.request.CreateImageRequest;
import com.example.bsuir.image.model.Image;
import com.example.bsuir.shared.dto.request.mapper.CreateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateImageMapper implements CreateMapper<Image, CreateImageRequest> {

    private final CharacterRepository characterRepository;

    @Override
    public Image toEntity(CreateImageRequest dto) {
        return new Image(
                dto.getKeyName(),
                dto.getFaceDescriptor(),
                characterRepository.findByIdOrThrow(dto.getCharacterId())
        );
    }
}
