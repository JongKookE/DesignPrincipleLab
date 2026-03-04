package com.subject.designprinciplelab.problem.seq06_global_exception_handling.exception;

public class OutOfStockException extends BusinessException {
    public OutOfStockException(ErrorCode errorCode) {
        super(errorCode);
    }
}