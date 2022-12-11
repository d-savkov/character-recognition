package com.example.bsuir.image.model;

import com.example.bsuir.character.model.Character;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import java.util.List;

@Node
@Getter
@Setter
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    private String path;

    private List<Double> faceDescriptor;

    @Relationship(type = "IMAGE", direction = Direction.INCOMING)
    private Character character;

    public Image(String path, List<Double> faceDescriptor, Character character) {
        this.path = path;
        this.faceDescriptor = faceDescriptor;
        this.character = character;
    }
}
