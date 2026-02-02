package com.subject.codesmellsolver.problem.seq05_decoupling_spring_event;

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
        S5Member s5Member = s5MemberRepository.save(new S5Member(name, email));

        eventPublisher.publishEvent(new S5MemberJoinedEvent(name, email));
    }
}