package com.mission.home_assignment.service;

import com.mission.home_assignment.exceptions.PersonAlreadyExistException;
import com.mission.home_assignment.exceptions.PersonNotFoundException;
import com.mission.home_assignment.model.person.Person;
import com.mission.home_assignment.repository.PersonDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonServiceInterface {
    @Autowired
    private PersonDao personDao;

    @Override
    public Person getPerson(String id) {
        return personDao.findById(id).orElseThrow(() -> new PersonNotFoundException("person with id : " + id + " not found", id));
    }

    @Override
    public List<Person> getAllPersons() {
        return personDao.findAll();
    }

    @Override
    public Person addPerson(Person person) {
        if (personDao.existsById(person.getId())) {
            throw new PersonAlreadyExistException("person with id : " + person.getId() + " already exist", person.getId());
        }
        return personDao.save(person);
    }

    @Override
    public Person updatePerson(Person person) {
        if (personDao.existsById(person.getId())) {
            return personDao.save(person);
        }
        throw new PersonNotFoundException("person with id : " + person.getId() + " not found", person.getId());
    }

    @Override
    public String deletePerson(String id) {
        if (personDao.existsById(id)) {
            personDao.deleteById(id);
            return id;
        }
        throw new PersonNotFoundException("person with id : " + id + " not found", id);
    }
}