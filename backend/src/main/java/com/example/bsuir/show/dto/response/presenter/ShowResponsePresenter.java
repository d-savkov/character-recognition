package com.example.bsuir.show.dto.response.presenter;

import com.example.bsuir.person.model.Person;
import com.example.bsuir.shared.dto.response.presenter.Presenter;
import com.example.bsuir.show.dto.response.ShowResponse;
import com.example.bsuir.show.model.Show;
import org.springframework.stereotype.Service;

@Service
public class ShowResponsePresenter implements Presenter<Show, ShowResponse> {

    @Override
    public ShowResponse toDto(Show entity) {
        return new ShowResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getShowType(),
                entity.getPersons().stream().map(Person::getId).toList()
        );
    }
}
