package com.subject.codesmellsolver.seq05;

import com.subject.codesmellsolver.problem.seq05_decoupling_spring_event.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class DecouplingEventTest {
    @Mock
    S5MemberRepository s5MemberRepository;

    @Mock
    ApplicationEventPublisher eventPublisher;

    @InjectMocks
    S5MemberService s5MemberService;
    
    @Test
    @DisplayName("ApplicationEventPublisher로 이벤트 처리를 수행하고 있는지 테스트")
    public void eventPublisherIsRunning(){
        String name = "Mock";
        String email = "Mock@ito";
        S5Member member = new S5Member(name, email);
        S5MemberJoinedEvent event = new S5MemberJoinedEvent(name, email);
        BDDMockito.given(s5MemberRepository.save(any(S5Member.class))).willReturn(member);

        s5MemberService.join(name, email);
        Mockito.verify(s5MemberRepository, Mockito.times(1)).save(any(S5Member.class));
        Mockito.verify(eventPublisher, Mockito.times(1)).publishEvent(any(S5MemberJoinedEvent.class));
    }

}