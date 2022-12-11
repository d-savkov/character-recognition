package com.example.bsuir.character.controller;

import com.example.bsuir.character.dto.response.CharacterResponse;
import com.example.bsuir.character.dto.response.presenter.CharacterPresenter;
import com.example.bsuir.character.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/show/{showId}/character")
@AllArgsConstructor
public class CharacterController {

    private final CharacterService service;
    private final CharacterPresenter presenter;

    @GetMapping()
    public List<CharacterResponse> getAllByShowId(@PathVariable Long showId) {
        return service.getAllByShowId(showId).stream()
                      .map(presenter::toDto)
                      .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CharacterResponse getById(@PathVariable Long showId, @PathVariable Long id) {
        return presenter.toDto(service.getById(id));
    }
}
