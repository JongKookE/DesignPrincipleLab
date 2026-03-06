package com.subject.designprinciplelab.problem.seq05_decoupling_spring_event.service;

import com.subject.designprinciplelab.problem.seq05_decoupling_spring_event.S5MemberJoinedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class S5EmailService {
    @EventListener
    @Async
    public void publishEmail(S5MemberJoinedEvent event) {
        // 메일 발송 로직
        System.out.println("이메일을 발행했습니다." + event.name());
    }
}