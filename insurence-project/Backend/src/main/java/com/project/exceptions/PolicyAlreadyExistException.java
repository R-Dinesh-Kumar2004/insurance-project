package com.project.exceptions;

public class PolicyAlreadyExistException extends RuntimeException {
    public PolicyAlreadyExistException(String message) {
        super(message);
    }
}
