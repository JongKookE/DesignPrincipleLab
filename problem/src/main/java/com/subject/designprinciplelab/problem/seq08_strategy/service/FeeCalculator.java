package com.subject.designprinciplelab.problem.seq08_strategy.service;

import com.subject.designprinciplelab.problem.seq08_strategy.constant.Country;

public interface FeeCalculator {
    double calculate(double weight, Country country);
}