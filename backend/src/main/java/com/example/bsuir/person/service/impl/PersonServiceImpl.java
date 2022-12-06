package com.example.bsuir.person.service.impl;

import com.example.bsuir.person.model.Person;
import com.example.bsuir.person.repository.PersonRepository;
import com.example.bsuir.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getById(Long id) {
        return personRepository.findByIdOrThrow(id);
    }

    @Override
    public Person getByImageId(Long imageId) {
        return personRepository.findByImageId(imageId).orElseThrow();
    }
}
