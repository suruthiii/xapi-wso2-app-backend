package com.example.apimauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ApiDetailResponse(
        String id,
        String name,
        String description,
        String context,
        String version,
        String provider,
        String lifeCycleStatus,
        boolean responseCachingEnabled,
        int cacheTimeout,
        boolean hasThumbnail,
        String type,
        List<String> transport,
        List<String> securityScheme,
        BusinessInfoDTO businessInformation,
        CorsConfigDTO corsConfiguration,
        List<OperationDTO> operations,
        AdvertiseInfoDTO advertiseInfo,
        @JsonProperty("createdTime") String createdTime,
        @JsonProperty("lastUpdatedTime") String lastUpdatedTime
) {}
