package com.mission.home_assignment.annotations;

import com.mission.home_assignment.model.state.State;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StateValidator implements ConstraintValidator<StateValidation, State> {
    @Override
    public boolean isValid(State state, ConstraintValidatorContext context) {

        if (state == null) {

            return false;
        }
        String stateName = state.name();

        if (stateName.equalsIgnoreCase("israel")) {
            return true;
        }
        return false;
    }

    @Override
    public void initialize(StateValidation constraintAnnotation) {
    }
}
