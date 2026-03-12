package com.subject.designprinciplelab.problem.seq08_strategy.domain;

import com.subject.designprinciplelab.problem.seq08_strategy.constant.Country;
import com.subject.designprinciplelab.problem.seq08_strategy.service.FeeCalculator;
import org.springframework.stereotype.Component;

@Component("Standard")
public class StandardDelivery implements FeeCalculator {
    @Override
    public double calculate(double weight, Country country) {
        return switch (country) {
            case KOREA -> 3000;
            case USA -> weight * 1.5 + 20000;
            case JAPAN -> weight * 1.2 + 15000;
        };
    }
}