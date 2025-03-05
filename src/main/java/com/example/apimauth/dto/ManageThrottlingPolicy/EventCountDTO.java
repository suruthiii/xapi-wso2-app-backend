package com.example.apimauth.dto.ManageThrottlingPolicy;

public record EventCountDTO(
        String timeUnit,
        int unitTime,
        int eventCount
) {}