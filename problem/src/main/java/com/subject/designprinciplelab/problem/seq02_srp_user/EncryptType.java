package com.subject.designprinciplelab.problem.seq02_srp_user;

import lombok.Getter;

@Getter
public enum EncryptType {
    RSA("RSA"),
    SALT("SALT"),
    SHA("SHA");

    private final String encryptType;

    EncryptType(String encryptType) {
        this.encryptType = encryptType;
    }
}
