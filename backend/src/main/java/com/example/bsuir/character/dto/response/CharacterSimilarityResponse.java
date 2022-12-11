package com.example.bsuir.character.dto.response;

import com.example.bsuir.character.model.Character;
import com.example.bsuir.shared.dto.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterSimilarityResponse implements Response<Character> {

    private CharacterResponse person;

    private BigDecimal similarity;
}
