package com.example.bsuir.person.model;

import com.example.bsuir.image.model.Image;
import com.example.bsuir.show.model.Show;
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
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String playedBy;

    private String description;

    private String imagesSource;

    @Relationship(type = "PERSON", direction = Direction.INCOMING)
    private Show show;

    @Relationship(type = "IMAGE", direction = Direction.OUTGOING)
    private List<Image> images = new ArrayList<>();

    public Person(String name, String description, String playedBy, String imagesSource, Show show) {
        this.name = name;
        this.playedBy = playedBy;
        this.description = description;
        this.imagesSource = imagesSource;
        this.show = show;
    }
}
