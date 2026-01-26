package com.subject.codesmellsolver.problem.seq02_srp_user;

public interface Encrypter {

    default String send(String password) {
        return "Encrypted Password: " + encrypt(password);
    }

    String encrypt(String password);
}
