package com.subject.codesmellsolver.problem.seq03_ocp_strategy;

import org.springframework.stereotype.Component;

@Component
public class S3SmtpSender implements S3NotificationSender {
    @Override
    public void send(String message) {
        System.out.println("SMTP: " + message);
    }
}