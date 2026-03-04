package com.subject.designprinciplelab.problem.seq05_decoupling_spring_event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class S5EmailSender {

    @EventListener
    @Async
    public void handleMemberJoined(S5MemberJoinedEvent event) {
        // 메일 발송 로직
        System.out.println("메일 발송: " + event.email());
    }
}