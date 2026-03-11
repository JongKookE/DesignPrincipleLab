package com.subject.designprinciplelab.seq07;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.subject.designprinciplelab.problem.seq07_concurrency_transactional")
public class CouponServiceTestApplication {
}
