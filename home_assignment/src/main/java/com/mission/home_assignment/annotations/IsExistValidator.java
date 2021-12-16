package com.mission.home_assignment.annotations;

import com.mission.home_assignment.annotations.IsExist;
import com.mission.home_assignment.model.person.Person;
import com.mission.home_assignment.repository.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsExistValidator implements ConstraintValidator<IsExist, String> {
    @Autowired
    private PersonDao personDao;

    @Override
    public boolean isValid(String personId, ConstraintValidatorContext constraintValidatorContext) {
        if (personId != null) {
            if (personDao.existsById(personId)) {
                return true;
            }
        }
        return false;
    }
}
