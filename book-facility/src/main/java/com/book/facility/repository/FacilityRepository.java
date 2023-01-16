package com.book.facility.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.book.facility.model.Facilities;

public interface FacilityRepository extends MongoRepository<Facilities, Integer> {

}
