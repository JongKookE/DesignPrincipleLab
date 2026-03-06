package com.subject.designprinciplelab.problem.seq05_decoupling_spring_event;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S5MemberService {
    private final S5MemberRepository s5MemberRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void join(String name, String email) {
        // @EventListener 어노테이션을 붙인 메소드 중에서 S5MemberJoinedEvent를 파라미터로 받는 메소드 호출
        // 비동기 작업이기 때문에 순서는 보장하지 못한다.
        eventPublisher.publishEvent(new S5MemberJoinedEvent(name, email));
    }
}