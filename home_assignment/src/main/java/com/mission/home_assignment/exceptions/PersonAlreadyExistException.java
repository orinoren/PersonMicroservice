package com.mission.home_assignment.exceptions;

public class PersonAlreadyExistException extends RuntimeException {

    private String message;

    private String personId;

    public PersonAlreadyExistException(String message, String personId) {
        this.message = message;
        this.personId = personId;
    }

    public String getMessage() {
        return message;
    }
}