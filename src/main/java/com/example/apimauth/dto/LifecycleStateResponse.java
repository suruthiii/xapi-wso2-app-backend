package com.example.apimauth.dto;

import java.util.List;

public record LifecycleStateResponse(
        String state,
        List<String> checkItems,
        List<Transition> availableTransitions
) {}
