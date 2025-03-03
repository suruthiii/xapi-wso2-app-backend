package com.example.apimauth.dto;

public record Transition(
        String event,
        String targetState
) {}