package com.example.apimauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ApiRevisionResponse(
        @JsonProperty("displayName") String displayName,
        @JsonProperty("id") String revisionId,
        String description,
        long createdTime,
        ApiInfo apiInfo,
        List<DeploymentInfo> deploymentInfo
) {}
