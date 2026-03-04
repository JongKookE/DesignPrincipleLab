package com.subject.designprinciplelab.problem.seq01_ocp_strategy;

public class Main {
    public static void main(String[] args) {
        NotificationManager manager = new NotificationManager();
        manager.sendNotification(new EmailSender(), "가입을 환영합니다.", "user@example.com");
        manager.sendNotification(new SMSSender(), "인증번호: 1234", "010-0000-0000");
        manager.sendNotification(new KakaoSender(), "안녕하세요", "kakao@kakao");
    }
}
