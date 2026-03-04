package com.subject.designprinciplelab.problem.seq01_ocp_strategy;

public class SMSSender implements NotificationSender {
    @Override
    public void sendNotification(String message, String recipient) {
        System.out.println("통신사 API 호출...");
        System.out.println("To: " + recipient + " | Msg: " + message);
    }
}

