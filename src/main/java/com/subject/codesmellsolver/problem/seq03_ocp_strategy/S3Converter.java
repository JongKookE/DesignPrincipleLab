package com.subject.codesmellsolver.problem.seq03_ocp_strategy;

import org.springframework.core.convert.converter.Converter;

public class S3Converter implements Converter<String, S3NotificationType> {
    @Override
    public S3NotificationType convert(String source) {
        return S3NotificationType.valueOf(source.toUpperCase());
    }
}
