package com.example.apimauth.dto;

import java.util.List;

public record OperationPoliciesDTO(
        List<Object> request,
        List<Object> response,
        List<Object> fault
) {}
