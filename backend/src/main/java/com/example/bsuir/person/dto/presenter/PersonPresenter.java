package com.example.bsuir.person.dto.presenter;

import com.example.bsuir.image.provider.ImageBase64Provider;
import com.example.bsuir.person.dto.PersonDto;
import com.example.bsuir.person.model.Person;
import com.example.bsuir.shared.dto.Presenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonPresenter implements Presenter<Person, PersonDto> {

    private final ImageBase64Provider imageBase64Provider;

    @Override
    public PersonDto toDto(Person entity) {
        return new PersonDto(
                entity.getId(),
                entity.getName(),
                entity.getPlayedBy(),
                entity.getDescription(),
                imageBase64Provider.get(entity)
        );
    }
}
