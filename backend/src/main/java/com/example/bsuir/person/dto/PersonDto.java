package com.example.bsuir.person.dto;

import com.example.bsuir.person.model.Person;
import com.example.bsuir.shared.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto implements Dto<Person> {

    private Long id;

    private String name;

    private String playedBy;

    private String description;

    private String base64Image;
}
