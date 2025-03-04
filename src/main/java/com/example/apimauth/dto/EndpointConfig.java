package com.example.apimauth.dto;

public record EndpointConfig(
        String endpoint_type,
        Endpoint sandbox_endpoints,
        Endpoint production_endpoints
) {}