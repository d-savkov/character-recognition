package com.example.bsuir.recognition.dto.response.presenter;

import com.example.bsuir.character.dto.response.presenter.CharacterPresenter;
import com.example.bsuir.character.model.Character;
import com.example.bsuir.recognition.dto.response.RecongitionResponse;
import com.example.bsuir.shared.dto.response.presenter.Presenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecognitionPresenter implements Presenter<Character, RecongitionResponse> {

    private final CharacterPresenter characterPresenter;

    @Override
    public RecongitionResponse toDto(Character entity) {
        return new RecongitionResponse(
                characterPresenter.toDto(entity),
                null
        );
    }
}
