package com.example.apimauth.dto.ManageGatewayPolicy;

public record GatewayPolicyResponse(
        String id,
        String description,
        String displayName
) {}