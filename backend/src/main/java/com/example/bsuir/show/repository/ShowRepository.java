package com.example.bsuir.show.repository;

import com.example.bsuir.show.model.Show;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ShowRepository extends Neo4jRepository<Show, Long> {
}
