package com.subject.codesmellsolver.problem.seq01_ocp_strategy;

public class NotificationManager {
    public void sendNotification(NotificationSender notificationSender, String message, String recipient) {
        notificationSender.sendNotification(message, recipient);
    }
}
