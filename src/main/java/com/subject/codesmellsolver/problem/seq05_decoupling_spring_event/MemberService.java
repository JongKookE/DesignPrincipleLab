package com.subject.codesmellsolver.problem.seq05_decoupling_spring_event;

import com.subject.codesmellsolver.problem.seq01_ocp_strategy.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    // [Problem] 부가 기능을 수행하는 서비스들에 직접 의존하고 있음
    private final EmailSender emailSender;
    private final CouponService couponService;

    @Transactional
    public void join(String name, String email) {
        // 1. 핵심 로직 (Core Concern)
        Member member = memberRepository.save(new Member(name, email));

        // 2. 부가 로직 (Side Effects)
        // 이 로직들이 실행되다 에러가 나면 회원가입까지 롤백되어야 할까요?
        // 혹은 이메일 서버가 느리면 회원가입 응답도 늦어져야 합니까?
        emailSender.sendWelcomeMail(email);
        couponService.issueWelcomeCoupon(member.getId());
    }
}