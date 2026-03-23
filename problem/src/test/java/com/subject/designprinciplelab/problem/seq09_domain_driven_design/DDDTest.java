package com.subject.designprinciplelab.problem.seq09_domain_driven_design;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.expression.JavaGetterMethodPropertySelector;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.subject.designprinciplelab.problem.seq09_domain_driven_design.domain.OrderRecord;
import com.subject.designprinciplelab.problem.seq09_domain_driven_design.domain.OrderStatus;
import com.subject.designprinciplelab.problem.seq09_domain_driven_design.repository.OrderRepository;
import com.subject.designprinciplelab.problem.seq09_domain_driven_design.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class DDDTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    private static final FixtureMonkey FIXTURE_MONKEY = FixtureMonkey
            .builder()
            .defaultNotNull(true)
            .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE) // Bean이 아닌 record를 주입받기 위해서 ConstructorProperties를 사용
            .build();
    OrderRecord orderRecord;


    @BeforeEach
    public void setup(){
        orderRecord = FIXTURE_MONKEY.giveMeBuilder(OrderRecord.class)
                .set(JavaGetterMethodPropertySelector.javaGetter(OrderRecord::orderStatus), OrderStatus.SHIPPED)
                .sample();
    }

    @Test
    public void executeCancelFail_ForAlreadyCancelOrShipped() throws Exception {
        given(orderRepository.findById(any())).willReturn(Optional.of(orderRecord.toEntity()));
        Assertions.assertThatThrownBy(() -> orderService.cancelOrder(orderRecord.id())).isInstanceOf(RuntimeException.class)
                                 .hasMessage("환불 불가능 상태");
    }


}