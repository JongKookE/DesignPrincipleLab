package com.subject.designprinciplelab.problem.seq09_domain_driven_design.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // PENDING, PAID, SHIPPED, CANCELED

    private boolean refundRequired;

    public void cancelOrder() throws Exception{
        if(this.status == OrderStatus.CANCELED || this.status == OrderStatus.SHIPPED) throw new RuntimeException("환불 불가능 상태");
        if(this.status == OrderStatus.PAID) this.refundRequired = true;
        this.status = OrderStatus.CANCELED;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", refundRequired=" + refundRequired +
                '}';
    }
}