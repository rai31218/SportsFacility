package com.sports.facility.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sports.facility.model.DatabaseSequence;

@Repository
public interface SequenceRepository extends MongoRepository<DatabaseSequence, String> {
  DatabaseSequence findTopByOrderByIdDesc();
}