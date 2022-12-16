package com.example.bsuir.character.dto.response;

import com.example.bsuir.character.model.Character;
import com.example.bsuir.image.dto.response.ImageResponse;
import com.example.bsuir.shared.dto.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterResponse implements Response<Character> {

    private Long id;

    private String name;

    private String playedBy;

    private String description;

    private Long showId;

    private List<ImageResponse> images;
}
