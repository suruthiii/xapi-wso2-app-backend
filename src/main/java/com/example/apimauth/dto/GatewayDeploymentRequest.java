package com.example.apimauth.dto;

public record GatewayDeploymentRequest(
        String gatewayLabel,
        boolean gatewayDeployment,
        String mappingUUID
) {}
