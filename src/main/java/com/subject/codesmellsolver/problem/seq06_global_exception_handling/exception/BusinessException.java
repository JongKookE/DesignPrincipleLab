package com.subject.codesmellsolver.problem.seq06_global_exception_handling.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    ErrorCode errorCode;
    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}