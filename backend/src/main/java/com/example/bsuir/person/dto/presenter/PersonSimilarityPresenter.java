package com.example.bsuir.person.dto.presenter;

import com.example.bsuir.person.dto.PersonDto;
import com.example.bsuir.person.dto.PersonSimilarityDto;
import com.example.bsuir.person.model.Person;
import com.example.bsuir.shared.dto.Presenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonSimilarityPresenter implements Presenter<Person, PersonSimilarityDto> {

    private final Presenter<Person, PersonDto> personDtoPresenter;

    @Override
    public PersonSimilarityDto toDto(Person entity) {
        PersonSimilarityDto dto = new PersonSimilarityDto();
        dto.setPerson(personDtoPresenter.toDto(entity));
        return dto;
    }
}
