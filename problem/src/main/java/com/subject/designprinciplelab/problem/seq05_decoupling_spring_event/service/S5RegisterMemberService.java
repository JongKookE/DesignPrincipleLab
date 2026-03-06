package com.subject.designprinciplelab.problem.seq05_decoupling_spring_event.service;

import com.subject.designprinciplelab.problem.seq05_decoupling_spring_event.S5Member;
import com.subject.designprinciplelab.problem.seq05_decoupling_spring_event.S5MemberJoinedEvent;
import com.subject.designprinciplelab.problem.seq05_decoupling_spring_event.S5MemberRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

@Service
@RequiredArgsConstructor
public class S5RegisterMemberService {
    private final S5MemberRepository s5MemberRepository;

    @Async
    @EventListener
    public void save(S5MemberJoinedEvent s5MemberJoinedEvent){
        String name = s5MemberJoinedEvent.name();
        String email = s5MemberJoinedEvent.email();
        s5MemberRepository.save(new S5Member(name, email));
        System.out.printf("S5Member를 레포지토리에 등록했습니다. =  %s", name);
    }
}