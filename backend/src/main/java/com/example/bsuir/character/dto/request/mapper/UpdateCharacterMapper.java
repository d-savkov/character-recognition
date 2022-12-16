package com.example.bsuir.character.dto.request.mapper;

import com.example.bsuir.character.dto.request.UpdateCharacterRequest;
import com.example.bsuir.character.model.Character;
import com.example.bsuir.character.repository.CharacterRepository;
import com.example.bsuir.shared.dto.request.mapper.UpdateMapper;
import com.example.bsuir.show.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCharacterMapper implements UpdateMapper<Character, UpdateCharacterRequest, Long> {

    private final CharacterRepository characterRepository;
    private final ShowRepository showRepository;

    @Override
    public Character toEntity(Long id, UpdateCharacterRequest dto) {
        Character character = characterRepository.findByIdOrThrow(id);
        character.setName(dto.getName());
        character.setDescription(dto.getDescription());
        character.setPlayedBy(dto.getPlayedBy());
        character.setShow(showRepository.findByIdOrThrow(dto.getShowId()));
        return character;
    }
}
