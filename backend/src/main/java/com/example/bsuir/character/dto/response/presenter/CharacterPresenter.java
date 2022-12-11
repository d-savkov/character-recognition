package com.example.bsuir.character.dto.response.presenter;

import com.example.bsuir.character.model.Character;
import com.example.bsuir.image.provider.ImageBase64Provider;
import com.example.bsuir.character.dto.response.CharacterResponse;
import com.example.bsuir.shared.dto.response.presenter.Presenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterPresenter implements Presenter<Character, CharacterResponse> {

    private final ImageBase64Provider imageBase64Provider;

    @Override
    public CharacterResponse toDto(Character entity) {
        return new CharacterResponse(
                entity.getId(),
                entity.getName(),
                entity.getPlayedBy(),
                entity.getDescription(),
                imageBase64Provider.get(entity)
        );
    }
}
