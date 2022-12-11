package com.example.bsuir.person.service;

import com.example.bsuir.person.model.Person;
import com.example.bsuir.shared.service.Service;

public interface PersonService extends Service<Person, Long> {

    Person getByImageId(Long imageId);
}
