package com.subject.designprinciplelab.problem.seq06_global_exception_handling.domain.dto;

public record ErrorResponse(
        String code, String message, String path
) {
}