package com.example.apimauth.dto.ManageAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeploymentInfo(
        @JsonProperty("revisionUuid") String revisionUuid,
        String name,
        String status,
        String vhost,
        @JsonProperty("displayOnDevportal") boolean displayOnDevPortal,
        Long deployedTime,
        Long successDeployedTime
) {}
