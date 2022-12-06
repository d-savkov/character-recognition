package com.example.bsuir.person.controller;

import com.example.bsuir.person.dto.PersonDto;
import com.example.bsuir.person.dto.presenter.PersonPresenter;
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

    private final PersonService personService;
    private final PersonPresenter personPresenter;

    @GetMapping()
    public List<PersonDto> getAll() {
        return personService.getAll().stream()
                            .map(personPresenter::toDto)
                            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDto getById(@PathVariable Long id) {
        return personPresenter.toDto(personService.getById(id));
    }
}
