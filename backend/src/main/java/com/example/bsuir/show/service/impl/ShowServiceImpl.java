package com.example.bsuir.show.service.impl;

import com.example.bsuir.character.model.Character;
import com.example.bsuir.character.service.CharacterService;
import com.example.bsuir.show.dto.request.CreateShowRequest;
import com.example.bsuir.show.dto.request.UpdateShowRequest;
import com.example.bsuir.show.dto.request.mapper.CreateShowRequestMapper;
import com.example.bsuir.show.dto.request.mapper.UpdateShowRequestMapper;
import com.example.bsuir.show.model.Show;
import com.example.bsuir.show.repository.ShowRepository;
import com.example.bsuir.show.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final ShowRepository repository;
    private final CreateShowRequestMapper createMapper;
    private final UpdateShowRequestMapper updateMapper;

    private final CharacterService characterService;

    @Override
    public Show getById(Long id) {
        return repository.findByIdOrThrow(id);
    }

    @Override
    public List<Show> getAll() {
        return repository.findAll();
    }

    @Override
    public Show create(CreateShowRequest request) {
        return repository.save(createMapper.toEntity(request));
    }

    @Override
    public Show updateById(Long id, UpdateShowRequest request) {
        return repository.save(updateMapper.toEntity(id, request));
    }

    @Override
    public void deleteById(Long id) {
        List<Character> characters = characterService.getAllByShowId(id);
        repository.deleteById(id);
        characters.stream()
                  .map(Character::getId)
                  .forEach(characterService::deleteById);
    }
}
