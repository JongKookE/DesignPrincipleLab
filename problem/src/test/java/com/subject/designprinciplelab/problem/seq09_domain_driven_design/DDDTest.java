package com.subject.designprinciplelab.problem.seq09_domain_driven_design;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.subject.designprinciplelab.problem.seq09_domain_driven_design.domain.Order;
import com.subject.designprinciplelab.problem.seq09_domain_driven_design.repository.OrderRepository;
import com.subject.designprinciplelab.problem.seq09_domain_driven_design.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
public class DDDTest {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private static final FixtureMonkey FIXTURE_MONKEY  = FixtureMonkey
            .builder()
            .defaultNotNull(true)
            .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE) // Bean이 아닌 record를 주입받기 위해서 ConstructorProperties를 사용
            .build();
    Order order;


    @BeforeEach
    public void setup(){
        orderRepository.deleteAll();
        order = FIXTURE_MONKEY.giveMeOne(Order.class);
        orderRepository.save(order);
    }

    @Test
    public void executeCancel() throws Exception {
        orderService.cancelOrder(order.getId());
    }


}