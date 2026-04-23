package com.subject.designprinciplelab.problem.seq10_circuit_breaker;

public class PgImpl implements PaymentService.PgClient {

    @Override
    public PaymentResult approve(Long orderId, double amount) {
        return new PaymentResult("APPROVED", 1L, 1000);
    }
}