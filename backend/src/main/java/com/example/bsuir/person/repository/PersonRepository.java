package com.example.bsuir.person.repository;

import com.example.bsuir.person.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    default Person findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new IllegalArgumentException(
                String.format("Can't find person with id %s", id)
        ));
    }

    default Person findByImageIdOrThrow(Long imageId) {
        return findByImageId(imageId).orElseThrow(() -> new IllegalArgumentException(
                String.format("Can't find person by image id %s", imageId)
        ));
    }

    @Query("MATCH (p:Person)-[:IMAGE]->(pi:Image) WHERE ID(pi) = $imageId RETURN p")
    Optional<Person> findByImageId(@Param("imageId") Long imageId);
}
