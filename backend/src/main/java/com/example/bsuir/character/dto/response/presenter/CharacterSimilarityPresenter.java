package com.example.bsuir.character.dto.response.presenter;

import com.example.bsuir.character.dto.response.CharacterResponse;
import com.example.bsuir.character.dto.response.CharacterSimilarityResponse;
import com.example.bsuir.character.model.Character;
import com.example.bsuir.shared.dto.response.presenter.Presenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterSimilarityPresenter implements Presenter<Character, CharacterSimilarityResponse> {

    private final Presenter<Character, CharacterResponse> personDtoPresenter;

    @Override
    public CharacterSimilarityResponse toDto(Character entity) {
        CharacterSimilarityResponse dto = new CharacterSimilarityResponse();
        dto.setCharacter(personDtoPresenter.toDto(entity));
        return dto;
    }
}
