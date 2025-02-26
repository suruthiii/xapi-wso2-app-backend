package com.example.apimauth.dto;

public record ClientRegistrationRequest(
        String callbackUrl,
        String clientName,
        String owner,
        String grantType,
        boolean saasApp
) {}
