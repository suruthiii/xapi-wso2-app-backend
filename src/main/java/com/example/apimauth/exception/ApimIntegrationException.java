package com.example.apimauth.exception;

import com.example.apimauth.dto.ErrorResponse;

public class ApimIntegrationException extends RuntimeException {
    private final ErrorResponse errorResponse;
    private final int statusCode;

    public ApimIntegrationException(ErrorResponse errorResponse, int statusCode) {
        super(errorResponse.message());
        this.errorResponse = errorResponse;
        this.statusCode = statusCode;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public int getStatusCode() {
        return statusCode;
    }
}