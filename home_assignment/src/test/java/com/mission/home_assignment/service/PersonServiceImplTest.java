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

@ExtendWith(MockitoExtension.class) // to close the resources automatically after each test.
class PersonServiceImplTest {

    @Mock
    private PersonDao personDao;

    private PersonServiceInterface personServiceTest;

    @BeforeEach
    void setUp() {
        personServiceTest = new PersonServiceImpl(personDao);
    }

    /**
     * 1. Create a person object. <br/>
     * 2. making the personDao.existsById(person.getId())) function to
     *    return true for continuing the test as wanted.<br/>
     * 3. checking if the personServiceTest.addPerson(person) method throws an error
     *    of {@link PersonAlreadyExistException)} and containing the excepted message.
     */
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

    /**
     * 1. Create a person object. <br/>
     * 2. making the personDao.existsById(person.getId())) function to
     *    return false for continuing the test as wanted.<br/>
     * 3. checking if the personServiceTest.updatePerson(person) method throws an error
     *    of {@link PersonNotFoundException)} and containing the excepted message.
     */
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

    /**
     * 1. create a String person id;<br/>
     * 2. making the personDao.existsById(person.getId())) function to
     *    return true for continuing the test as wanted.<br/>
     *  3. extracting the expected id and calling {@link PersonServiceImpl#deletePerson(String personId)}.<br/>
     *  4. checking equality of the expected id the person id that created at start.
     */
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