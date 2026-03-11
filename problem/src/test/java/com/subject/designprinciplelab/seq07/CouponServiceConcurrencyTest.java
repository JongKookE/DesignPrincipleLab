package com.subject.designprinciplelab.seq07;

import com.subject.designprinciplelab.problem.seq07_concurrency_transactional.domain.Coupon;
import com.subject.designprinciplelab.problem.seq07_concurrency_transactional.repository.CouponIssueRepository;
import com.subject.designprinciplelab.problem.seq07_concurrency_transactional.repository.CouponRepository;
import com.subject.designprinciplelab.problem.seq07_concurrency_transactional.service.CouponService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CouponServiceTestApplication.class)
class CouponServiceConcurrencyTest {
    private long couponId;
    private int totalQuantity;
    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponIssueRepository couponIssueRepository;

    @BeforeEach
    void setUp() {
        totalQuantity = 100;
        couponIssueRepository.deleteAll();
        couponRepository.deleteAll();
        Coupon coupon = couponRepository.save(new Coupon(null, "first-come coupon", totalQuantity, 0));
        couponId = coupon.getId();
    }

    @Test
    @DisplayName("200 users requesting 100 coupons concurrently should preserve data consistency")
    void shouldIssueOnly100CouponsWhen200UsersRequestConcurrently() throws InterruptedException {
        int threadCount = 200;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(threadCount);
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failedCount = new AtomicInteger();

        try (ExecutorService executorService = Executors.newFixedThreadPool(32)) {
            for (long userId = 1; userId <= threadCount; userId++) {
                long finalUserId = userId;
                executorService.submit(() -> {
                    try {
                        startLatch.await();
                        couponService.issue(couponId, finalUserId);
                        successCount.incrementAndGet();
                    } catch (Throwable throwable) {
                        failedCount.incrementAndGet();
                    } finally {
                        doneLatch.countDown();
                    }
                });
            }

            startLatch.countDown();
            doneLatch.await();
        }

        Coupon coupon = couponRepository.findById(couponId).orElseThrow();

        assertThat(successCount.get()).isEqualTo(totalQuantity);
        // totalQuantity가 threadCount 보다 작으면 실패횟수가 음수가 되지만 지금은 고려하지 않았음
        assertThat(failedCount.get()).isEqualTo(threadCount - totalQuantity);
        assertThat(couponIssueRepository.countByCouponId(couponId)).isEqualTo(100);
        assertThat(coupon.getIssuedQuantity()).isEqualTo(100);
        assertThat(coupon.getRemainingQuantity()).isZero();
    }
}