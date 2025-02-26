package com.example.apimauth.dto;

public record OperationDTO(
        String target,
        String verb,
        String authType,
        String throttlingPolicy,
        OperationPoliciesDTO operationPolicies
) {}
