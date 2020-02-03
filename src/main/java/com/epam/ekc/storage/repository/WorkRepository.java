package com.epam.ekc.storage.repository;

import com.epam.ekc.storage.model.Work;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends MongoRepository<Work, String> {
}
