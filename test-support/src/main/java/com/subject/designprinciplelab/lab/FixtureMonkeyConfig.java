package com.subject.designprinciplelab.lab.fixture;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.BeanArbitraryIntrospector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FixtureMonkeyConfig {

    @Bean
    public FixtureMonkey fixtureMonkey() {
        return FixtureMonkey
                .builder()
                .defaultNotNull(true)
                .objectIntrospector(BeanArbitraryIntrospector.INSTANCE)
                .build();
    }
}
