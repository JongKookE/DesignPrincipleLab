package com.subject.designprinciplelab.problem.seq09_domain_driven_design.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="order")
@Getter
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // PENDING, PAID, SHIPPED, CANCELED
    @Setter
    private boolean refundRequired;

    public void cancelOrder() throws Exception{
        if(this.status == OrderStatus.CANCELED || this.status == OrderStatus.SHIPPED) throw new Exception("환불 불가능 상태");
        if(this.status == OrderStatus.PAID) this.refundRequired = true;
        this.status = OrderStatus.CANCELED;
    }
}