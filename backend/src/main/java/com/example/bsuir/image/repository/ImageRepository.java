package com.example.bsuir.image.repository;

import com.example.bsuir.exception.EntityNotFoundException;
import com.example.bsuir.image.model.Image;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface ImageRepository extends Neo4jRepository<Image, Long> {

    default Image findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Can't find Image with id=%s", id))
        );
    }

    List<Image> findAllByCharacterId(Long characterId);
}
