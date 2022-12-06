package com.example.bsuir.person.service;

import com.example.bsuir.person.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAll();

    Person getById(Long id);

    Person getByImageId(Long imageId);
}
