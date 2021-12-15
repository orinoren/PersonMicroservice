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

    /**
     * Calling {@link PersonDao#findById(Object id)} to get the match person from the database.
     * if no match Throws {@link PersonNotFoundException}.
     *
     * @param id person id to get
     * @return Person that match the id input
     * @throws PersonNotFoundException if person not found by the id input.
     */
    @Override
    public Person getPerson(String id) throws PersonNotFoundException {
        return personDao.findById(id).orElseThrow(() -> new PersonNotFoundException("person with id : " + id + " not found", id));
    }
    @Override
    public List<Person> getAllPersons() {
        return personDao.findAll();
    }

    /**
     * Calling {@link PersonDao#existsById(Object personId)} to check if this person is not
     * already exist.
     * if exist Throws {@link PersonAlreadyExistException}.
     * if not exist Calling {@link PersonDao#save(Object person)} to save the person in the database.
     *
     * @param person
     * @return Person that added to the database.
     * @throws PersonAlreadyExistException if person is already exist.
     */
    @Override
    public Person addPerson(Person person) throws PersonAlreadyExistException {
        if (personDao.existsById(person.getId())) {
            throw new PersonAlreadyExistException("person with id : " + person.getId() + " already exist", person.getId());
        }
        return personDao.save(person);
    }

    /**
     * Calling {@link PersonDao#existsById(Object personId)} to check if this person exists.
     * if exist Calling {@link PersonDao#save(Object person)} to save the person in the database.
     * if not exist  Throws  {@link PersonNotFoundException}.
     *
     * @param person
     * @return Person after the update .
     * @throws PersonNotFoundException if person not found in the database.
     */
    @Override
    public Person updatePerson(Person person) throws PersonNotFoundException {
        if (personDao.existsById(person.getId())) {
            return personDao.save(person);
        }
        throw new PersonNotFoundException("person with id : " + person.getId() + " not found", person.getId());
    }

    /**
     * Calling {@link PersonDao#existsById(Object personId)} to check if this person exists.
     * if exist Calling {@link PersonDao#deleteById(Object personId)} to save the person in the database.
     * if not exist Throws {@link PersonNotFoundException}.
     *
     * @param id person id
     * @return String id of the deleted person.
     * @throws PersonNotFoundException if person not found in the database.
     */
    @Override
    public String deletePerson(String id) throws PersonNotFoundException {
        if (personDao.existsById(id)) {
            personDao.deleteById(id);
            return id;
        }
        throw new PersonNotFoundException("person with id : " + id + " not found", id);
    }
}