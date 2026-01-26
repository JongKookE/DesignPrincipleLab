package com.subject.codesmellsolver.problem.seq02_srp_user;

class RSAEncrypter implements Encrypter {
    @Override
    public String encrypt(String password) {
        return "RSA-" + password;
    }
}
