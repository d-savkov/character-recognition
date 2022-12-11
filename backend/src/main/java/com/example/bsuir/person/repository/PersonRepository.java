package com.example.bsuir.person.repository;

import com.example.bsuir.exception.EntityNotFoundException;
import com.example.bsuir.person.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    default Person findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Can't find Person with id=%s", id))
        );
    }

    @Query("MATCH (p:Person)-[:IMAGE]->(pi:Image) WHERE ID(pi) = $imageId RETURN p")
    Optional<Person> findByImageId(@Param("imageId") Long imageId);
}
