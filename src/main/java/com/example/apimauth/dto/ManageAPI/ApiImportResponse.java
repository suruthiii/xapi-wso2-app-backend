package com.example.apimauth.dto.ManageAPI;

public record ApiImportResponse(
        String id,
        String name,
        String version,
        String context,
        String status
) {}