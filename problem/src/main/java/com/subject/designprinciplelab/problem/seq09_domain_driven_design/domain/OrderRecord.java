package com.subject.designprinciplelab.problem.seq09_domain_driven_design.domain;

public record OrderRecord(
        long id, OrderStatus orderStatus, boolean refundRequired
) {
    public Order toEntity(){
        return new Order(
                id,
                orderStatus,
                refundRequired
        );
    }
}