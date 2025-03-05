package com.example.apimauth.dto.ManageAPI;

public record BusinessInfoDTO(
        String businessOwner,
        String businessOwnerEmail,
        String technicalOwner,
        String technicalOwnerEmail
) {}
