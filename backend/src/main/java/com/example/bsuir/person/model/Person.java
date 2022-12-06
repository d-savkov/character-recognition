package com.example.bsuir.person.model;

import com.example.bsuir.image.model.Image;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String playedBy;

    private String description;

    @Relationship(type = "IMAGE", direction = Direction.OUTGOING)
    private List<Image> images = new ArrayList<>();

    private String imagesSource;

    public Person(String name, String description, String playedBy, String imagesSource) {
        this.name = name;
        this.playedBy = playedBy;
        this.description = description;
        this.imagesSource = imagesSource;
    }
}
