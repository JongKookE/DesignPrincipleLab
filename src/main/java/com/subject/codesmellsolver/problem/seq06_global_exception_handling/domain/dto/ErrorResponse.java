package com.subject.codesmellsolver.problem.seq06_global_exception_handling.domain.dto;

import com.subject.codesmellsolver.problem.seq06_global_exception_handling.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

public record ErrorResponse(
        String code, String message, String path
) {
}