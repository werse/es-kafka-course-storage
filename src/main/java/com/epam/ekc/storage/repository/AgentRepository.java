package com.epam.ekc.storage.repository;

import com.epam.ekc.storage.model.Agent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgentRepository extends MongoRepository<Agent, String> {
}
