package com.subject.designprinciplelab.problem.seq08_strategy.service;

import com.subject.designprinciplelab.problem.seq08_strategy.constant.Country;
import com.subject.designprinciplelab.problem.seq08_strategy.constant.DeliveryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final Map<String, FeeCalculator> deliveryCalculators;

    public double calculateDeliveryFee(DeliveryType type, double weight, Country country) {
        FeeCalculator feeCalculator = deliveryCalculators.get(type.getType());
        if (feeCalculator == null) {
            throw new IllegalArgumentException("Unsupported delivery type: " + type);
        }

        return feeCalculator.calculate(weight, country);
    }
}