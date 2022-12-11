package com.example.bsuir.show.model;

import com.example.bsuir.character.model.Character;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import java.util.ArrayList;
import java.util.List;

@Node
@Getter
@Setter
@NoArgsConstructor
public class Show {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private ShowType showType;

    @Relationship(type = "CHARACTER", direction = Direction.OUTGOING)
    private List<Character> characters = new ArrayList<>();

    public Show(String name, String description, ShowType showType) {
        this.name = name;
        this.description = description;
        this.showType = showType;
    }
}
