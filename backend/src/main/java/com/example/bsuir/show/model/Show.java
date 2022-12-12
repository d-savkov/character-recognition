package com.example.bsuir.show.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Show {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private ShowType showType;

    public Show(String name, String description, ShowType showType) {
        this.name = name;
        this.description = description;
        this.showType = showType;
    }
}
