package com.example.bsuir.person.controller;

import com.example.bsuir.person.dto.response.PersonResponse;
import com.example.bsuir.person.dto.response.presenter.PersonPresenter;
import com.example.bsuir.person.service.PersonService;
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
@RequestMapping(value = "/wiki")
@AllArgsConstructor
public class PersonWikiController {

    private final PersonService service;
    private final PersonPresenter presenter;

    @GetMapping()
    public List<PersonResponse> getAll() {
        return service.getAll().stream()
                      .map(presenter::toDto)
                      .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonResponse getById(@PathVariable Long id) {
        return presenter.toDto(service.getById(id));
    }
}
