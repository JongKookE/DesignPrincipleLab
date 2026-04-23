package com.subject.designprinciplelab.problem.seq10_circuit_breaker;

import lombok.Getter;

@Getter
public class PaymentResult {
    private final String status;
    private final Long orderId;
    private final double amount;

    public PaymentResult(String status, Long orderId, double amount) {
        this.status = status;
        this.orderId = orderId;
        this.amount = amount;
    }
}