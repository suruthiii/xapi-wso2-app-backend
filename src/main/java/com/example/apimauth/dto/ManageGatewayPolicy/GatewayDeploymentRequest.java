package com.example.apimauth.dto.ManageGatewayPolicy;

public record GatewayDeploymentRequest(
        String gatewayLabel,
        boolean gatewayDeployment,
        String mappingUUID
) {}
