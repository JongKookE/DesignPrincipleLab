package com.subject.designprinciplelab.problem.seq03_ocp_strategy;

import lombok.Getter;

@Getter
public enum S3NotificationType {
    SMS("s3SmsSender"),
    KAKAO("s3KakaoSender"),
    EMAIL("s3EmailSender"),
    SLACK("s3SlackSender");

    private final String type;

    S3NotificationType(String type) {
        this.type = type;
    }
}