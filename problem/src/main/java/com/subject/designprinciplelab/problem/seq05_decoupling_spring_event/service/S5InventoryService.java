package com.subject.designprinciplelab.problem.seq05_decoupling_spring_event.service;

import com.subject.designprinciplelab.problem.seq05_decoupling_spring_event.S5MemberJoinedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class S5InventoryService {
    @EventListener
    @Async
    public void saveMyInventory(S5MemberJoinedEvent event) {
        // 메일 발송 로직
        System.out.println("창고에 저장했습니다." + event.name());
    }
}