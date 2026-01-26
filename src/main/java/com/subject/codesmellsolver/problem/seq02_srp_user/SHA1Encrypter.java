package com.subject.codesmellsolver.problem.seq02_srp_user;

class SHA1Encrypter implements Encrypter {
    @Override
    public String encrypt(String password) {
        return "SHA-" + password;
    }
}
