package com.subject.designprinciplelab.problem.seq06_global_exception_handling.exception;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}