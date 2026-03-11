package com.subject.designprinciplelab.lab.fixture;

import com.navercorp.fixturemonkey.FixtureMonkey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(com.subject.designprinciplelab.lab.fixture.FixtureMonkeyConfig.class)
class FixtureMonkeyTest {

    @Autowired
    private FixtureMonkey fixtureMonkey;

    @Test
    @DisplayName("FixtureMonkey bean is injected from FixtureMonkeyConfig")
    void fixtureMonkeyBeanIsInjected() {
        Assertions.assertNotNull(fixtureMonkey);
    }

    @RepeatedTest(10)
    @DisplayName("FixtureMonkey from config can create a sample object")
    void fixtureMonkeyCanCreateObject() {
        SetterProbe actual = fixtureMonkey.giveMeBuilder(SetterProbe.class)
                .set("name", "park")
                .set("canFly", false)
                .sample();

        assertThat(actual.getName()).isEqualTo("park");
        assertThat(actual.getValue()).isNotEqualTo(null);
        assertThat(actual.getHeight()).isNotEqualTo(null);
        assertThat(actual.getCanFly()).isEqualTo(false);
    }

    @Getter
    @Setter
    @ToString
    public static class SetterProbe {
        private String value;
        private String name;
        private Long height;
        private Boolean canFly;
    }
}