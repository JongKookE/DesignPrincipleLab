package com.subject.designprinciplelab.problem.seq05_decoupling_spring_event.service;

import com.subject.designprinciplelab.problem.seq05_decoupling_spring_event.S5MemberJoinedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class S5PushNotificationService {

    @Async
    @EventListener
    public void PushNoty(S5MemberJoinedEvent event) {
        // 메일 발송 로직
        System.out.println("알람을 보냈습니다." + event.name());
    }
}