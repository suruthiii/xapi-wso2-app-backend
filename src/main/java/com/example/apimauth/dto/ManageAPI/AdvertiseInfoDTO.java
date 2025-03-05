package com.example.apimauth.dto.ManageAPI;

public record AdvertiseInfoDTO(
        boolean advertised,
        String apiExternalProductionEndpoint,
        String apiExternalSandboxEndpoint,
        String apiOwner,
        String vendor
) {}