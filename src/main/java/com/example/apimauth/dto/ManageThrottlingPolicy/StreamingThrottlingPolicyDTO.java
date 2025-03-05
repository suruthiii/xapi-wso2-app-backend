package com.example.apimauth.dto.ManageThrottlingPolicy;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StreamingThrottlingPolicyDTO(
        @JsonProperty("policyId") int policyId,
        String uuid,
        String policyName,
        String displayName,
        String description,
        boolean isDeployed,
        int tenantId,
        String tenantDomain,
        DefaultLimitDTO defaultLimit,
        int rateLimitCount,
        String rateLimitTimeUnit,
        int subscriberCount,
        List<Object> customAttributes,
        boolean stopOnQuotaReach,
        String billingPlan,
        Object permissions
) {}
