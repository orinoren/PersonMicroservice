package com.mission.home_assignment.service;

import com.mission.home_assignment.exceptions.PersonAlreadyExistException;
import com.mission.home_assignment.exceptions.PersonNotFoundException;
import com.mission.home_assignment.model.address.Address;
import com.mission.home_assignment.model.gender.Gender;
import com.mission.home_assignment.model.person.Person;
import com.mission.home_assignment.model.state.State;
import com.mission.home_assignment.repository.PersonDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonDao personDao;

    private PersonServiceInterface personServiceTest;

    @BeforeEach
    void setUp() {
        personServiceTest = new PersonServiceImpl(personDao);
    }

    @Test
    @DisplayName("Throw exception because person id already exist")
    public void addPersonTest() {
        //given
        Address address = new Address(State.ISRAEL, "Jerusalem", "Yamin", "213214", true);
        Person person = new Person("213", "avi", 50, Gender.MALE, 50d, 50d, address);

        given(personDao.existsById(person.getId())).willReturn(true);

        //when
        //then
        assertThatThrownBy(() -> personServiceTest.addPerson(person))
                .isInstanceOf(PersonAlreadyExistException.class)
                .hasMessageContaining("person with id : " + person.getId() + " already exist", person.getId());

    }

    @Test
    @DisplayName("Throw exception because person not found")
    public void updatePersonTest() {
        //given
        Address address = new Address(State.ISRAEL, "Jerusalem", "Yamin", "213214", true);
        Person person = new Person("212433", "avi", 50, Gender.MALE, 50d, 50d, address);
        given(personDao.existsById(person.getId())).willReturn(false);
        //when
        //then
        assertThatThrownBy(() -> personServiceTest.updatePerson(person))
                .isInstanceOf(PersonNotFoundException.class)
                .hasMessageContaining("person with id : " + person.getId() + " not found", person.getId());

    }

    @Test
    @DisplayName("Is delete person made successfully")
    public void deletePersonTest() {
        //given
        String personId = "3223";
        given(personDao.existsById(personId)).willReturn(true);
        //when
        String expected = personServiceTest.deletePerson(personId);
        //then
        assertEquals(personId, expected);
    }
}