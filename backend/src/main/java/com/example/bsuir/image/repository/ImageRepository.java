package com.example.bsuir.image.repository;

import com.example.bsuir.image.model.Image;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ImageRepository extends Neo4jRepository<Image, Long> {

}
