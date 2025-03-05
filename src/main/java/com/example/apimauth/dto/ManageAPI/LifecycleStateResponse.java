package com.example.apimauth.dto.ManageAPI;

import java.util.List;

public record LifecycleStateResponse(
        String state,
        List<String> checkItems,
        List<Transition> availableTransitions
) {}
