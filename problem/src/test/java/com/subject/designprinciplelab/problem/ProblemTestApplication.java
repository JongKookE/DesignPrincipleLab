package com.subject.designprinciplelab.problem;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.subject.designprinciplelab.problem.seq04_aop")
public class ProblemTestApplication {
}
