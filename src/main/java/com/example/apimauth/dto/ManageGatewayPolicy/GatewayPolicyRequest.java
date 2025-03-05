package com.example.apimauth.dto.ManageGatewayPolicy;

public record GatewayPolicyRequest(
        String id,
        PolicyMappingDTO policyMapping,
        String description,
        String displayName
) {}
