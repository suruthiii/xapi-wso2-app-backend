package com.example.apimauth.dto.ManageGatewayPolicy;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GatewayPolicyDetailDTO(
        String id,
        PolicyMappingDTO policyMapping,
        String description,
        String displayName,
        @JsonProperty("appliedGatewayLabels") List<String> gatewayLabels
) {}
