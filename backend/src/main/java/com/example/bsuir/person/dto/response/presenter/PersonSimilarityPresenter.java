package com.example.bsuir.person.dto.response.presenter;

import com.example.bsuir.person.dto.response.PersonResponse;
import com.example.bsuir.person.dto.response.PersonSimilarityResponse;
import com.example.bsuir.person.model.Person;
import com.example.bsuir.shared.dto.response.presenter.Presenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonSimilarityPresenter implements Presenter<Person, PersonSimilarityResponse> {

    private final Presenter<Person, PersonResponse> personDtoPresenter;

    @Override
    public PersonSimilarityResponse toDto(Person entity) {
        PersonSimilarityResponse dto = new PersonSimilarityResponse();
        dto.setPerson(personDtoPresenter.toDto(entity));
        return dto;
    }
}
