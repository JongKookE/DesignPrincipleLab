package com.subject.designprinciplelab.seq06;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.subject.designprinciplelab.problem.seq06_global_exception_handling.GlobalExceptionHandler;
import com.subject.designprinciplelab.problem.seq06_global_exception_handling.OrderController;
import com.subject.designprinciplelab.problem.seq06_global_exception_handling.OrderRequest;
import com.subject.designprinciplelab.problem.seq06_global_exception_handling.OrderService;
import com.subject.designprinciplelab.problem.seq06_global_exception_handling.exception.ErrorCode;
import com.subject.designprinciplelab.problem.seq06_global_exception_handling.exception.OutOfStockException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ExceptionHandleTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;


    FixtureMonkey fixtureMonkey;

    @Test
    @DisplayName("OrderService가 예외를 던지면 GlobalExceptionHandler가 실패 응답을 반환한다")
    void handlerFailException() throws Exception {

        OrderRequest order = fixtureMonkey.giveMeBuilder(OrderRequest.class)
                .set("productId", 1L)
                .set("quantity", 10)
                .sample();

        Assertions.assertThat(order.productId()).isEqualTo(1L);
        Assertions.assertThat(order.quantity()).isEqualTo(10);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        given(orderService.placeOrder(any(OrderRequest.class)))
                .willThrow(new OutOfStockException(ErrorCode.OUT_OF_STOCK));

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "productId": 1,
                                  "quantity": 10
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.data.code").value("S001"));
    }
}