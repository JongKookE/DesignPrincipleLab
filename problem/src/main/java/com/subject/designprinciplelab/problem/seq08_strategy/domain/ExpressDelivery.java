package com.subject.designprinciplelab.problem.seq08_strategy.domain;

import com.subject.designprinciplelab.problem.seq08_strategy.constant.Country;
import com.subject.designprinciplelab.problem.seq08_strategy.service.FeeCalculator;
import org.springframework.stereotype.Component;

@Component("Express")
public class ExpressDelivery implements FeeCalculator {

    @Override
    public double calculate(double weight, Country country) {
        return switch (country) {
            case KOREA -> 5000;
            case USA -> weight * 3.0 + 40000;
            case JAPAN -> weight * 2.5 + 30000;
        };
    }

}