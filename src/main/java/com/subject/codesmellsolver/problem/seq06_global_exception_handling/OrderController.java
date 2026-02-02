package com.subject.codesmellsolver.problem.seq06_global_exception_handling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequest request) {
        try {
            Long orderId = orderService.placeOrder(request.productId(), request.quantity());
            return ResponseEntity.ok(orderId);
        } catch (IllegalArgumentException e) {
            // 상품이 없을 때의 처리
            return ResponseEntity.status(404).body("Product Not Found: " + e.getMessage());
        } catch (IllegalStateException e) {
            // 재고가 없을 때의 처리
            return ResponseEntity.status(400).body("Sold Out: " + e.getMessage());
        } catch (Exception e) {
            // 알 수 없는 에러
            return ResponseEntity.status(500).body("Internal Error occurred");
        }
    }
}