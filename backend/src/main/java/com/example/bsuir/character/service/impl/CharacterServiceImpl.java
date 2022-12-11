package com.example.bsuir.character.service.impl;

import com.example.bsuir.character.model.Character;
import com.example.bsuir.character.repository.CharacterRepository;
import com.example.bsuir.character.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    @Override
    public List<Character> getAll() {
        return characterRepository.findAll();
    }

    @Override
    public Character getById(Long id) {
        return characterRepository.findByIdOrThrow(id);
    }

    @Override
    public List<Character> getAllByShowId(Long showId) {
        return characterRepository.findAllByShowId(showId);
    }

    @Override
    public Character getByImageId(Long imageId) {
        return characterRepository.findByImageId(imageId).orElseThrow();
    }
}
