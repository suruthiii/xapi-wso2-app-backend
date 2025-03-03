package com.example.apimauth.dto;

public record LifecycleUpdateResponse(
        String workflowStatus,
        String jsonPayload,
        LifecycleStateResponse lifecycleState
) {}

