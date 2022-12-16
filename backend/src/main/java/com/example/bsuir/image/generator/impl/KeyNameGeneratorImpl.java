package com.example.bsuir.image.generator.impl;

import com.example.bsuir.character.model.Character;
import com.example.bsuir.character.service.CharacterService;
import com.example.bsuir.image.generator.KeyNameGenerator;
import com.example.bsuir.image.model.Image;
import com.example.bsuir.image.service.ImageService;
import com.example.bsuir.show.model.Show;
import com.example.bsuir.show.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeyNameGeneratorImpl implements KeyNameGenerator {

    private final CharacterService characterService;
    private final ShowService showService;
    private final ImageService imageService;

    @Override
    public String generate(Long showId, Long characterId, String fileType) {
        Show show = showService.getById(showId);
        Character character = characterService.getById(characterId);
        List<Image> images = imageService.getAllByCharacterId(characterId);
        return String.format(
                "%s/%s/%d.%s",
                show.getName().toLowerCase().replace(' ', '-'),
                character.getName().toLowerCase().replace(' ', '-'),
                images.size() + 1,
                fileType
        );
    }
}
