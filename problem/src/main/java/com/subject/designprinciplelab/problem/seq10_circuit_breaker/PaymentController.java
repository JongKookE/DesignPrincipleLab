package com.subject.designprinciplelab.problem.seq10_circuit_breaker;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{orderId}/{amount}")
    public PaymentResult getResult(@PathVariable("orderId") Long orderId,
                                   @PathVariable("amount") Double amount) throws Exception {
        return paymentService.processPayment(orderId, amount);
    }

}