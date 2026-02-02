package com.subject.codesmellsolver.problem.seq06_global_exception_handling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    public Long placeOrder(Long productId, int quantity) {
        // 상품 조회 로직 (가정)
        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }

        // 재고 확인 로직 (가정)
        if (quantity > 100) {
            throw new IllegalStateException("Not enough stock for product: " + productId);
        }

        // 주문 생성 로직...
        return 1001L;
    }
}