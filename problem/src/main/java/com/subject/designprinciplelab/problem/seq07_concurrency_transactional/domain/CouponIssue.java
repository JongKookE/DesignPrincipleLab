package com.subject.designprinciplelab.problem.seq07_concurrency_transactional.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(
        name = "coupon_issue",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_coupon_issue_coupon_user",
                        columnNames = {"coupon_id", "user_id"}
                )
        }
)
public class CouponIssue {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long couponId;
    private Long userId;
}