package com.subject.codesmellsolver.problem.seq05_decoupling_spring_event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S5CouponService {

    @EventListener
    @Async
    public void issueWelcomeCoupon(S5MemberJoinedEvent event) {
        // 메일 발송 로직
        System.out.println("쿠폰을 발급했습니다.: " + event.name());
    }

}