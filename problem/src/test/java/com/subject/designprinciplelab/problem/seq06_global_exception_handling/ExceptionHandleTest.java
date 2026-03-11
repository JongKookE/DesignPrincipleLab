package com.subject.designprinciplelab.problem.seq06_global_exception_handling;

import com.navercorp.fixturemonkey.ArbitraryBuilder;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.subject.designprinciplelab.problem.seq06_global_exception_handling.exception.ErrorCode;
import com.subject.designprinciplelab.problem.seq06_global_exception_handling.exception.OutOfStockException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

import static com.navercorp.fixturemonkey.api.expression.JavaGetterMethodPropertySelector.javaGetter;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ExceptionHandleTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Mock으로 선언한 클래스 중 생성자로 선언할 수 있는 클래스가 있다면 이를 자동으로 주입해줌
    // MockitoExtension.class가 해줌
    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    // test-module에 fixtureMonkey를 선언했지만 스프링 컨텍스트를 로드하면 단위 테스트보단 통합 테스트의 성격이 강해서 직접 선언
    private static final FixtureMonkey FIXTURE_MONKEY  = FixtureMonkey
            .builder()
            .defaultNotNull(true)
            .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE) // Bean이 아닌 record를 주입받기 위해서 ConstructorProperties를 사용
            .build();

    private static final ArbitraryBuilder<OrderRequest> ORDER_REQUEST_BUILDER =
            FIXTURE_MONKEY.giveMeBuilder(OrderRequest.class)
                    .set(javaGetter(OrderRequest::productId), 1L)
                    .setPostCondition(req -> req.quantity() < 10);

    @RepeatedTest(100)
    @DisplayName("Fixture Monkey가 생성한 객체에 제약조건과 랜덤 데이터를 주입한다")
    void createObject_byFixtureMonkey(){
        OrderRequest order = ORDER_REQUEST_BUILDER.sample();

        Assertions.assertThat(order.productId()).isNotNull();
        Assertions.assertThat(order.quantity()).isLessThan(10);
    }

    @Test
    @DisplayName("OrderService가 예외를 던지면 GlobalExceptionHandler가 실패 응답을 반환한다")
    void handlerFailException() throws Exception {
        OrderRequest order = ORDER_REQUEST_BUILDER.set(javaGetter(OrderRequest::quantity), 5).sample();

        Assertions.assertThat(order.productId()).isEqualTo(1L);
        Assertions.assertThat(order.quantity()).isEqualTo(5);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        given(orderService.placeOrder(any(OrderRequest.class)))
                .willThrow(new OutOfStockException(ErrorCode.OUT_OF_STOCK));

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.data.code").value("S001"));
    }
}