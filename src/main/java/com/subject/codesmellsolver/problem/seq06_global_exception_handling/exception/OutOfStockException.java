package com.subject.codesmellsolver.problem.seq06_global_exception_handling.exception;

public class OutOfStockException extends BusinessException {
    public OutOfStockException(ErrorCode errorCode) {
        super(errorCode);
    }
}