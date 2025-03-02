package com.example.apimauth.dto;

import java.util.Map;

public record PolicyMappingItemDTO(
        String policyName,
        String policyVersion,
        String policyId,
        Map<String, Object> parameters
) {}
