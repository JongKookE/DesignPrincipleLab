package com.subject.codesmellsolver.problem.seq03_ocp_strategy;

import lombok.Getter;

@Getter
public enum S3NotificationType {
    SMS("SMS"),
    KAKAO("KAKAO"),
    EMAIL("EMAIL"),
    SLACK("SLACK");

    private final String type;

    S3NotificationType(String type) {
        this.type = type;
    }
}
