package com.example.apimauth.dto.ManageAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeploymentRequest(
        String name,
        String vhost,
        @JsonProperty("displayOnDevportal") boolean displayOnDevPortal
) {}
