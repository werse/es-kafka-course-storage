package com.epam.ekc.storage.repository;

import com.epam.ekc.storage.model.Instance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceRepository extends MongoRepository<Instance, String> {
}
