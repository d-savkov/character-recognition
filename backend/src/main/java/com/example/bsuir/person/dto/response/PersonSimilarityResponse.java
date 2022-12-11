package com.example.bsuir.person.dto.response;

import com.example.bsuir.person.model.Person;
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
public class PersonSimilarityResponse implements Response<Person> {

    private PersonResponse person;

    private BigDecimal similarity;
}
