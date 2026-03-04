package com.subject.designprinciplelab.problem.seq01_ocp_strategy;

public class EmailSender implements NotificationSender {
    @Override
    public void sendNotification(String message, String recipient) {
        System.out.println("이메일 서버 연결...");
        System.out.println("To: " + recipient + " | Body: " + message);
    }
}