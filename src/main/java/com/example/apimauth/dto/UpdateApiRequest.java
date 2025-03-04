package com.example.apimauth.dto;

import java.util.List;
import java.util.Map;

public record UpdateApiRequest(
        String name,
        String description,
        String context,
        String version,
        String provider,
        String lifeCycleStatus,
        boolean responseCachingEnabled,
        int cacheTimeout,
        boolean hasThumbnail,
        boolean isDefaultVersion,
        String type,
        String audience,
        List<String> audiences,
        List<String> transport,
        List<String> tags,
        List<String> policies,
        String apiThrottlingPolicy,
        String authorizationHeader,
        String apiKeyHeader,
        List<String> securityScheme,
        Map<String, Integer> maxTps,
        String visibility,
        List<String> visibleRoles,
        List<String> visibleTenants,
        String subscriptionAvailability,
        List<String> subscriptionAvailableTenants,
        EndpointConfig endpointConfig,
        Map<String, Object> monetization,
        CorsConfigDTO corsConfiguration,
        List<OperationDTO> operations
) {}
