package com.example.apimauth.dto.ManageAPI;

import com.example.apimauth.dto.ManageOperationPolicy.OperationPoliciesDTO;

public record OperationDTO(
        String target,
        String verb,
        String authType,
        String throttlingPolicy,
        OperationPoliciesDTO operationPolicies
) {}
