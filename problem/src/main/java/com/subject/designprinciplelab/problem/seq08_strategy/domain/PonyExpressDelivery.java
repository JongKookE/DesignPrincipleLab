package com.subject.designprinciplelab.problem.seq08_strategy.domain;

import com.subject.designprinciplelab.problem.seq08_strategy.constant.Country;
import com.subject.designprinciplelab.problem.seq08_strategy.service.FeeCalculator;
import org.springframework.stereotype.Component;

@Component("PonyExpress")
public class PonyExpressDelivery implements FeeCalculator {
    @Override
    public double calculate(double weight, Country country) {
        return weight * 10.0 + 100_000;
    }

}