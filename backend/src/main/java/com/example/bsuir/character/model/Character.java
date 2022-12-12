package com.example.bsuir.character.model;

import com.example.bsuir.show.model.Show;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Character {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String playedBy;

    private String description;

    @Relationship(type = "CHARACTER_OF", direction = Direction.OUTGOING)
    private Show show;

    public Character(String name, String description, String playedBy, Show show) {
        this.name = name;
        this.playedBy = playedBy;
        this.description = description;
        this.show = show;
    }
}
