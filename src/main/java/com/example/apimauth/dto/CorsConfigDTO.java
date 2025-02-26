package com.example.apimauth.dto;

import java.util.List;

public record CorsConfigDTO(
        boolean corsConfigurationEnabled,
        List<String> accessControlAllowOrigins,
        boolean accessControlAllowCredentials,
        List<String> accessControlAllowHeaders,
        List<String> accessControlAllowMethods
) {}
