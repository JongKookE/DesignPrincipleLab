package com.subject.codesmellsolver.problem.seq02_srp_user;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public void save(String email, String password, String name){}
    public boolean existsByEmail(String email){return false;}
}