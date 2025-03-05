package com.example.apimauth.dto.ManageThrottlingPolicy;

import java.util.Map;

public record ThrottlingPolicyDTO(
        String name,
        String description,
        String policyLevel,
        String displayName,
        Map<String, Object> attributes,
        Integer requestCount,
        String dataUnit,
        Integer totalTokenCount,
        Integer promptTokenCount,
        Integer completionTokenCount,
        Integer unitTime,
        String timeUnit,
        Integer rateLimitCount,
        String rateLimitTimeUnit,
        String quotaPolicyType,
        String tierPlan,
        boolean stopOnQuotaReach,
        Map<String, Object> monetizationProperties
) {}