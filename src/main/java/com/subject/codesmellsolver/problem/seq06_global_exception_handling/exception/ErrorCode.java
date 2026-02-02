package com.subject.codesmellsolver.problem.seq06_global_exception_handling.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    EntityNotFoundException("EntityNotFoundException", 404, "찾을수없습니다."),
    OutOfStockException("OutOfStockException", 400, "만료되었습니다.");

    final private String name;
    final private int code;
    final private String message;
}