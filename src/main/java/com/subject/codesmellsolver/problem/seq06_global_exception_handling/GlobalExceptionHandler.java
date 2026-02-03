package com.subject.codesmellsolver.problem.seq06_global_exception_handling;

import com.subject.codesmellsolver.problem.seq06_global_exception_handling.domain.dto.ApiResponse;
import com.subject.codesmellsolver.problem.seq06_global_exception_handling.domain.dto.ErrorResponse;
import com.subject.codesmellsolver.problem.seq06_global_exception_handling.exception.BusinessException;
import com.subject.codesmellsolver.problem.seq06_global_exception_handling.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiResponse<ErrorResponse>> handleBusinessException(BusinessException e, HttpServletRequest req) {
        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse errorResponse = new ErrorResponse(
                errorCode.getCode(),
                e.getMessage(),
                req.getRequestURI()
        );

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResponse.fail(errorResponse));
    }
}