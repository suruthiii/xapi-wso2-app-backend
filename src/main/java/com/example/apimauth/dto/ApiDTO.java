package com.example.apimauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiDTO(
        String id,
        String name,
        String description,
        String context,
        String version,
        String provider,
        String type,
        String subtype,
        String lifeCycleStatus,
        @JsonProperty("createdTime") String createdTime,
        @JsonProperty("updatedTime") String updatedTime,
        boolean hasThumbnail,
        boolean monetizedInfo,
        String businessOwner,
        String businessOwnerEmail,
        String technicalOwner,
        String technicalOwnerEmail
) {}
