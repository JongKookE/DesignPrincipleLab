package com.subject.designprinciplelab.problem.seq06_global_exception_handling;

import com.subject.designprinciplelab.problem.seq06_global_exception_handling.domain.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<OrderRequest>> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(ApiResponse.success(orderService.placeOrder(-1L, 100)));
    }
}