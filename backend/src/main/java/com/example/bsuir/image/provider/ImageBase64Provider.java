package com.example.bsuir.image.provider;

import com.example.bsuir.person.model.Person;

public interface ImageBase64Provider {

    String get(Person person);
}
