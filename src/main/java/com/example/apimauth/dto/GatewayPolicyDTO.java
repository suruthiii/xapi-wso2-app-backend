package com.example.apimauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record GatewayPolicyDTO(
        String id,
        String description,
        String displayName,
        @JsonProperty("appliedGatewayLabels") List<String> gatewayLabels
) {}