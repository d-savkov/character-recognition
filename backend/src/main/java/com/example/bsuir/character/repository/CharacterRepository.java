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

    @Query("MATCH (s:Show)-[:CHARACTER]->(c:Character) WHERE ID(s) = $showId RETURN c")
    List<Character> findAllByShowId(@Param("showId") Long showId);

    @Query("MATCH (c:Character)-[:IMAGE]->(i:Image) WHERE ID(i) = $imageId RETURN c")
    Optional<Character> findByImageId(@Param("imageId") Long imageId);
}
