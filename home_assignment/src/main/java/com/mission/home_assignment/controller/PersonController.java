package com.mission.home_assignment.controller;

import com.mission.home_assignment.model.person.Person;
import com.mission.home_assignment.service.PersonServiceInterface;
import com.mission.home_assignment.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/")
public class PersonController {

    @Autowired
    private PersonServiceInterface personService;

    @Autowired
    private Response response;

    @GetMapping("person/{personId}")
    public ResponseEntity<Person> getPerson(@PathVariable("personId") String personId) {
        Person person = personService.getPerson(personId);
        return ResponseEntity.ok(person);
    }

    @GetMapping("persons")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

    @Transactional(readOnly = false)
    @PostMapping("person")
    public ResponseEntity<Person> addPerson(@Valid @RequestBody Person person) {
        System.out.println(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.addPerson(person));
    }

    @Transactional(readOnly = false)
    @PutMapping("person")
    public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person) {
        return ResponseEntity.ok(personService.updatePerson(person));
    }

    @Transactional(readOnly = false)
    @DeleteMapping("person/{id}")
    public ResponseEntity<Response> deletePerson(@PathVariable("id") String personId) {
        String deletedPersonId = personService.deletePerson(personId);
        Map<String, String> errors = new HashMap<>();
        errors.put("Delete", "person with id " + personId + " has been deleted successfully");
        response.setErrors(errors);
        return ResponseEntity.ok(response);
    }
}
