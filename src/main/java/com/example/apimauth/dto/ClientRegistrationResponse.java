package com.example.apimauth.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ClientRegistrationResponse(
        @JsonProperty("clientId") String clientId,
        @JsonProperty("clientSecret") String clientSecret
) {}
