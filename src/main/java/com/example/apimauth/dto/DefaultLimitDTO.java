package com.example.apimauth.dto;

public record DefaultLimitDTO(
        String type,
        Integer requestCount,
        Integer bandwidth,
        EventCountDTO eventCount
) {}
