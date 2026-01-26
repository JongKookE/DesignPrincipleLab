package com.subject.codesmellsolver.problem.seq02_srp_user;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    void save(String email, String password, String name) {
        System.out.println("DB 연결...");
        System.out.println("INSERT INTO users VALUES (" + email + ", " + password + ", " + name + ")");
    }
}
