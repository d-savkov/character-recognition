package com.example.bsuir.character.service.impl;

import com.example.bsuir.character.dto.request.CreateCharacterRequest;
import com.example.bsuir.character.dto.request.UpdateCharacterRequest;
import com.example.bsuir.character.dto.request.mapper.CreateCharacterMapper;
import com.example.bsuir.character.dto.request.mapper.UpdateCharacterMapper;
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
    private final CreateCharacterMapper createMapper;
    private final UpdateCharacterMapper updateMapper;

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
    public Character create(CreateCharacterRequest request) {
        return repository.save(createMapper.toEntity(request));
    }

    @Override
    public Character updateById(Long id, UpdateCharacterRequest request) {
        return repository.save(updateMapper.toEntity(id, request));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
