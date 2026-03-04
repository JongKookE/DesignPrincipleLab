package com.subject.designprinciplelab.problem.seq02_srp_user;

class RSAEncrypter implements Encrypter {
    @Override
    public String encrypt(String password) {
        return "RSA-" + password;
    }
}
