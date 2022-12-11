package com.example.bsuir.person.dto.response.presenter;

import com.example.bsuir.image.provider.ImageBase64Provider;
import com.example.bsuir.person.dto.response.PersonResponse;
import com.example.bsuir.person.model.Person;
import com.example.bsuir.shared.dto.response.presenter.Presenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonPresenter implements Presenter<Person, PersonResponse> {

    private final ImageBase64Provider imageBase64Provider;

    @Override
    public PersonResponse toDto(Person entity) {
        return new PersonResponse(
                entity.getId(),
                entity.getName(),
                entity.getPlayedBy(),
                entity.getDescription(),
                imageBase64Provider.get(entity)
        );
    }
}
