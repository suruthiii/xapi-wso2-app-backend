package com.example.apimauth.dto;

public record EventCountDTO(
        String timeUnit,
        int unitTime,
        int eventCount
) {}