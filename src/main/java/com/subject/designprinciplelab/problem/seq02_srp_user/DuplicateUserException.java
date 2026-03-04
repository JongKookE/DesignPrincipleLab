package com.subject.designprinciplelab.problem.seq02_srp_user;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String message) {
        super(message);
    }
}