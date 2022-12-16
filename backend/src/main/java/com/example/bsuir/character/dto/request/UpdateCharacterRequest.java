package com.example.bsuir.character.dto.request;

import com.example.bsuir.character.model.Character;
import com.example.bsuir.shared.dto.request.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCharacterRequest implements Request<Character> {

    private String name;

    private String playedBy;

    private String description;

    private Long showId;
}
