package com.subject.codesmellsolver.problem.seq02_srp_user;

import org.springframework.stereotype.Component;

@Component
public class SmtpSender {
    public void sendToSMTPServer(String email) {
        System.out.println("SMTP 서버 연결...");
        System.out.println("To: " + email + " | Body: 가입을 환영합니다!");
    }
}