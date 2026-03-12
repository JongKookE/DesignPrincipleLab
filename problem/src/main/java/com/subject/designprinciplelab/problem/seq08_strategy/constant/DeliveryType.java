package com.subject.designprinciplelab.problem.seq08_strategy.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryType {
    STANDARD("Standard"),
    EXPRESS("Express"),
    PONY_EXPRESS("PonyExpress");

    private final String type;
}