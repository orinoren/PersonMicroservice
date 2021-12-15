package com.mission.home_assignment.repository;

import com.mission.home_assignment.model.person.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends MongoRepository<Person, String> {
}
