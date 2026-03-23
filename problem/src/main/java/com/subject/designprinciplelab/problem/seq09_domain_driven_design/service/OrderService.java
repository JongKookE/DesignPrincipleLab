package com.subject.designprinciplelab.problem.seq09_domain_driven_design.service;

import com.subject.designprinciplelab.problem.seq09_domain_driven_design.domain.Order;
import com.subject.designprinciplelab.problem.seq09_domain_driven_design.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public void cancelOrder(Long orderId) throws Exception{
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.cancelOrder();
        orderRepository.save(order);
    }
}