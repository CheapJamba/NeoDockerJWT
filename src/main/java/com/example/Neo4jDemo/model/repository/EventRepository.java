package com.example.Neo4jDemo.model.repository;

import com.example.Neo4jDemo.model.Event;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface EventRepository extends Neo4jRepository<Event, Long> {


}
