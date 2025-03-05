package com.example.apimauth.dto.ManageAPI;

public record Transition(
        String event,
        String targetState
) {}