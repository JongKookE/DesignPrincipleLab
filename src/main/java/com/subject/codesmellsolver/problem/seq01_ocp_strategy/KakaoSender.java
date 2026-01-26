package com.subject.codesmellsolver.problem.seq01_ocp_strategy;

public class KakaoSender implements NotificationSender {
    @Override
    public void sendNotification(String message, String recipient) {
        System.out.println("카카오톡 게이트웨이 연결...");
        System.out.println("To: " + recipient + " | Msg: " + message);
    }
}
