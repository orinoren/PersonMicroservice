package com.mission.home_assignment.annotations;

import com.mission.home_assignment.model.state.State;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * StateValidator
 * the logic of @StateValidation
 * checks if the state input is valid by checking if it is equal to ISRAEL.
 */
public class StateValidator implements ConstraintValidator<StateValidation, State> {
    @Override
    public boolean isValid(State state, ConstraintValidatorContext context) {
        if (state != null) {
            String stateName = state.name();
            if (stateName.equals("ISRAEL")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize(StateValidation constraintAnnotation) {
    }
}
