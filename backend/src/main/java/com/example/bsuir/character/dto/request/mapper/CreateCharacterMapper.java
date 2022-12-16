package com.example.bsuir.character.dto.request.mapper;

import com.example.bsuir.character.dto.request.CreateCharacterRequest;
import com.example.bsuir.character.model.Character;
import com.example.bsuir.shared.dto.request.mapper.CreateMapper;
import com.example.bsuir.show.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCharacterMapper implements CreateMapper<Character, CreateCharacterRequest> {

    private final ShowRepository showRepository;

    @Override
    public Character toEntity(CreateCharacterRequest dto) {
        return new Character(
                dto.getName(),
                dto.getDescription(),
                dto.getPlayedBy(),
                showRepository.findByIdOrThrow(dto.getShowId())
        );
    }
}
