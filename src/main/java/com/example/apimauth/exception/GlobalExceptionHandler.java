package com.example.apimauth.exception;

import com.example.apimauth.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApimIntegrationException.class)
    public ResponseEntity<ErrorResponse> handleApimIntegrationException(ApimIntegrationException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(ex.getErrorResponse());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(
                        "INTERNAL_ERROR",
                        "Internal server error",
                        ex.getMessage(),
                        null,
                        null
                ));
    }
}