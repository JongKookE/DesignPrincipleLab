package com.subject.designprinciplelab.problem.seq10_circuit_breaker;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PgClient pgClient = new PgImpl();

    @CircuitBreaker(name = "pg-circuit", fallbackMethod = "processPaymentFallback")
    public PaymentResult processPayment(Long orderId, double amount) throws Exception {
        if (orderId == 2) throw new Exception("의도된 에러, fallbackMethod는 아직 동작되지 않음");
        return pgClient.approve(orderId, amount);
    }

    public PaymentResult processPaymentFallback(Long orderId, double amount, Throwable t) {
        log.error("PG payment failed. orderId={}, amount={}", orderId, amount, t);
        return new PaymentResult("PENDING", orderId, amount);
    }

    public interface PgClient {
        PaymentResult approve(Long orderId, double amount);
    }
}