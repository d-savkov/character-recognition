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

    private final CharacterRepository repository;

    @Override
    public List<Character> getAll() {
        return repository.findAll();
    }

    @Override
    public Character getById(Long id) {
        return repository.findByIdOrThrow(id);
    }

    @Override
    public List<Character> getAllByShowId(Long showId) {
        return repository.findAllByShowId(showId);
    }

    @Override
    public Character getByImageId(Long imageId) {
        Character character = repository.findByImageIdOrThrow(imageId);
        return repository.findByIdOrThrow(character.getId());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
