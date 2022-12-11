package com.example.bsuir.image.model;

import com.example.bsuir.person.model.Person;
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
    private Person person;

    public Image(String path, List<Double> faceDescriptor, Person person) {
        this.path = path;
        this.faceDescriptor = faceDescriptor;
        this.person = person;
    }
}
