package com.subject.designprinciplelab.problem.seq02_srp_user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptConfig {

    @Bean
    public Encrypter encrypter() {
        return new SHA1Encrypter();
    }
}