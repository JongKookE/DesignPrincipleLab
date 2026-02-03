package com.subject.codesmellsolver.problem.seq06_global_exception_handling;

import com.subject.codesmellsolver.problem.seq06_global_exception_handling.exception.BusinessException;
import com.subject.codesmellsolver.problem.seq06_global_exception_handling.exception.EntityNotFoundException;
import com.subject.codesmellsolver.problem.seq06_global_exception_handling.exception.ErrorCode;
import com.subject.codesmellsolver.problem.seq06_global_exception_handling.exception.OutOfStockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    public OrderRequest placeOrder(Long productId, int quantity) {
        // 상품 조회 로직 (가정)
        if (productId == null || productId <= 0) {
            throw new EntityNotFoundException(ErrorCode.PRODUCT_NOT_FOUND);
        }

        // 재고 확인 로직 (가정)
        if (quantity > 100) {
            throw new OutOfStockException(ErrorCode.OUT_OF_STOCK);
        }

        // 주문 생성 로직...
        return new OrderRequest(1L, 20);
    }
}