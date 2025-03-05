package com.example.apimauth.dto.ManageAPI;

public record LifecycleUpdateResponse(
        String workflowStatus,
        String jsonPayload,
        LifecycleStateResponse lifecycleState
) {}

