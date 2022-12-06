package com.example.bsuir.person.dto;

import com.example.bsuir.person.model.Person;
import com.example.bsuir.shared.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonSimilarityDto implements Dto<Person> {

    private PersonDto person;

    private BigDecimal similarity;
}
