package com.subject.codesmellsolver.problem.seq02_srp_user;

import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    void validate(String email, String password) {
        // 1. 이메일 검증 로직
        if (!email.contains("@")) throw new IllegalArgumentException("유효하지 않은 이메일입니다.");
        // 2. 비밀번호 보안 검사 로직
        if (password.length() < 8) throw new IllegalArgumentException("비밀번호는 8자 이상이어야 합니다.");
    }
}
