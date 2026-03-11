package com.subject.designprinciplelab.problem.seq07_concurrency_transactional.repository;

import com.subject.designprinciplelab.problem.seq07_concurrency_transactional.domain.Coupon;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Coupon> findWithLockById(Long id);
}