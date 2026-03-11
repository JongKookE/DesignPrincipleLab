package com.subject.designprinciplelab.problem.seq05_decoupling_spring_event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DecouplingEventTest {
    @Mock
    S5MemberRepository s5MemberRepository;

    @Mock
    ApplicationEventPublisher eventPublisher;

    @InjectMocks
    S5MemberService s5MemberService;

    @Test
    @DisplayName("join 호출 시 회원가입 이벤트를 발행한다")
    public void join_publishMemberJoinedEvent() {
        String name = "Mock";
        String email = "Mock@ito";

        s5MemberService.join(name, email);

        ArgumentCaptor<S5MemberJoinedEvent> eventCaptor = ArgumentCaptor.forClass(S5MemberJoinedEvent.class);
        Mockito.verify(eventPublisher, Mockito.times(1)).publishEvent(eventCaptor.capture());

        S5MemberJoinedEvent event = eventCaptor.getValue();
        assertEquals(name, event.name());
        assertEquals(email, event.email());
    }
}