package com.example.bsuir.show.repository;

import com.example.bsuir.exception.EntityNotFoundException;
import com.example.bsuir.show.model.Show;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ShowRepository extends Neo4jRepository<Show, Long> {

    default Show findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Can't find Show with id=%s", id))
        );
    }
}
