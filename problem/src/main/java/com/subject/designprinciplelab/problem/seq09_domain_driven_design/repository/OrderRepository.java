package com.subject.designprinciplelab.problem.seq09_domain_driven_design.repository;

import com.subject.designprinciplelab.problem.seq09_domain_driven_design.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}