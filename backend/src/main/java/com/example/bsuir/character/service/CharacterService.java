package com.example.bsuir.character.service;

import com.example.bsuir.character.model.Character;
import com.example.bsuir.shared.service.Service;

import java.util.List;

public interface CharacterService extends Service<Character, Long> {

    List<Character> getAllByShowId(Long showId);

    Character getByImageId(Long imageId);

    void deleteById(Long id);
}
