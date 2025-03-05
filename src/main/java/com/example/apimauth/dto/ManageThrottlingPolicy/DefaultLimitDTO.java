package com.example.apimauth.dto.ManageThrottlingPolicy;

public record DefaultLimitDTO(
        String type,
        Integer requestCount,
        Integer bandwidth,
        EventCountDTO eventCount
) {}
