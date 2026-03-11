package com.subject.designprinciplelab.problem.seq07_concurrency_transactional.repository;

import com.subject.designprinciplelab.problem.seq07_concurrency_transactional.domain.CouponIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponIssueRepository extends JpaRepository<CouponIssue, Long> {
    boolean existsByCouponIdAndUserId(Long couponId, Long userId);
    long countByCouponId(Long couponId);
}