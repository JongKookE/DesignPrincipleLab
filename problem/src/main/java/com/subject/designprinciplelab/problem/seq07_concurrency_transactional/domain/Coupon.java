package com.subject.designprinciplelab.problem.seq07_concurrency_transactional.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Coupon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int totalQuantity;
    private int issuedQuantity;

    public int getRemainingQuantity() {
        return totalQuantity - issuedQuantity;
    }

    public void issue() {
        if (getRemainingQuantity() <= 0) {
            throw new IllegalStateException("쿠폰 재고가 없습니다.");
        }
        issuedQuantity++;
    }
}