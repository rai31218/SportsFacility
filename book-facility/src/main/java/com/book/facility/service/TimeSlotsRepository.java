package com.book.facility.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.book.facility.model.TimeSlots;



public interface TimeSlotsRepository extends MongoRepository<TimeSlots, Integer> {

}
