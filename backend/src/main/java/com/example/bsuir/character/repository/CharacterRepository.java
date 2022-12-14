package com.example.bsuir.character.repository;

import com.example.bsuir.character.model.Character;
import com.example.bsuir.exception.EntityNotFoundException;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends Neo4jRepository<Character, Long> {

    default Character findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Can't find Character with id=%s", id))
        );
    }

    default Character findByImageIdOrThrow(Long imageId) {
        return findByImageId(imageId).orElseThrow(
                () -> new EntityNotFoundException(String.format("Can't find Character by imageId=%s", imageId))
        );
    }

    List<Character> findAllByShowId(Long showId);

    @Query("MATCH (i:Image)-[:IMAGE_OF]->(c:Character) WHERE ID(i) = $imageId RETURN c")
    Optional<Character> findByImageId(@Param("imageId") Long imageId);
}
