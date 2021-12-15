package com.mission.home_assignment.service;

import com.mission.home_assignment.model.person.Person;

import java.util.List;
import java.util.Optional;

public interface PersonServiceInterface {
    Person getPerson(String id);
    List<Person> getAllPersons();
    Person addPerson(Person person);
    Person updatePerson(Person person);
    String deletePerson(String id);
}
