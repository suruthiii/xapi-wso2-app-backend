package com.example.apimauth.dto;

public record GatewayPolicyRequest(
        String id,
        PolicyMappingDTO policyMapping,
        String description,
        String displayName
) {}
