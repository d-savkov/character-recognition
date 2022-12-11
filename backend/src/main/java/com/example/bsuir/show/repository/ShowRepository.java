package com.example.bsuir.show.repository;

import com.example.bsuir.exception.EntityNotFoundException;
import com.example.bsuir.show.model.Show;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShowRepository extends Neo4jRepository<Show, Long> {

    default Show findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Can't find Show with id=%s", id))
        );
    }

    @Query("MATCH (s:Show)-[:CHARACTER]->(c:Character) WHERE ID(c) = $characterId RETURN s")
    Optional<Show> findByCharacterId(@Param("characterId") Long characterId);
}
