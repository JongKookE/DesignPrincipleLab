package com.subject.codesmellsolver.problem.seq01_ocp_strategy;

import org.springframework.stereotype.Service;

@Service
public interface NotificationSender {
    void sendNotification(String message, String recipient);
}
