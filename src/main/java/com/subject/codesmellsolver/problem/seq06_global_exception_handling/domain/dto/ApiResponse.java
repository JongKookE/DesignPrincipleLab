package com.subject.codesmellsolver.problem.seq06_global_exception_handling.domain.dto;

import com.subject.codesmellsolver.problem.seq06_global_exception_handling.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        boolean success,
        LocalDateTime timestamp,
        T data
) {
    public static <T> ApiResponse<T> fail(T errorData) {
        return new ApiResponse<>(false, LocalDateTime.now(), errorData);
    }

    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(
                true,
                LocalDateTime.now(),
                data
        );
    }
}