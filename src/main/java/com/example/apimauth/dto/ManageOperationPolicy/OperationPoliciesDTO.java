package com.example.apimauth.dto.ManageOperationPolicy;

import java.util.List;

public record OperationPoliciesDTO(
        List<Object> request,
        List<Object> response,
        List<Object> fault
) {}
