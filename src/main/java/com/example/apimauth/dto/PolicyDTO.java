package com.example.apimauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record PolicyDTO(
        String category,
        String id,
        String name,
        String version,
        String displayName,
        String description,
        List<String> applicableFlows,
        List<String> supportedGateways,
        List<String> supportedApiTypes,
        @JsonProperty("isAPISpecific") boolean apiSpecific,
        String md5,
        List<PolicyAttribute> policyAttributes
) {}
