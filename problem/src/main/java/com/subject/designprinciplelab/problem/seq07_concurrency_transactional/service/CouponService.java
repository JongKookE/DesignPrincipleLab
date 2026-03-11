package com.subject.designprinciplelab.problem.seq07_concurrency_transactional.service;

import com.subject.designprinciplelab.problem.seq07_concurrency_transactional.domain.Coupon;
import com.subject.designprinciplelab.problem.seq07_concurrency_transactional.domain.CouponIssue;
import com.subject.designprinciplelab.problem.seq07_concurrency_transactional.repository.CouponIssueRepository;
import com.subject.designprinciplelab.problem.seq07_concurrency_transactional.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final CouponIssueRepository couponIssueRepository;

    @Transactional
    public void issue(Long couponId, Long userId) {
        // findWithLockById 메소드에서 비관적 쓰기 락을 걸어 조회하는 시점에서 락을 선점
        Coupon coupon = couponRepository.findWithLockById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("쿠폰이 없습니다."));

        if (couponIssueRepository.existsByCouponIdAndUserId(couponId, userId)) {
            throw new IllegalStateException("이미 발급받은 사용자입니다.");
        }

        coupon.issue();

        // existsByCouponIdAndUserId로 조회당시에는 쿠폰발급이 받지 않았음을 보장하지만 실제로 save를 할때는 쿠폰발급을 보장하지 못하기 때문에
        // DB unique key를 통해 최종 보장
        // Lock을 사용하지 않은 이유는 존재하지 않는 row에는 Lock을 걸 수 없기때문
        try{
            couponIssueRepository.save(new CouponIssue(null, couponId, userId));
        } catch (DataIntegrityViolationException e){
            throw new IllegalStateException("이미 발급받은 사용자입니다.", e);
        }

    }
}
