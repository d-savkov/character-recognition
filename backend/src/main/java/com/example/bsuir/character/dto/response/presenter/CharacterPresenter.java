package com.example.bsuir.character.dto.response.presenter;

import com.example.bsuir.character.dto.response.CharacterResponse;
import com.example.bsuir.character.model.Character;
import com.example.bsuir.image.dto.response.presenter.ImagePresenter;
import com.example.bsuir.image.repository.ImageRepository;
import com.example.bsuir.shared.dto.response.presenter.Presenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterPresenter implements Presenter<Character, CharacterResponse> {

    private final ImagePresenter imagePresenter;
    private final ImageRepository imageRepository;

    @Override
    public CharacterResponse toDto(Character entity) {
        return new CharacterResponse(
                entity.getId(),
                entity.getName(),
                entity.getPlayedBy(),
                entity.getDescription(),
                entity.getShow().getId(),
                imageRepository.findAllByCharacterId(entity.getId()).stream().map(imagePresenter::toDto).toList()
        );
    }
}
