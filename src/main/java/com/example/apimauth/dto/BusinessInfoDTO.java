package com.example.apimauth.dto;

public record BusinessInfoDTO(
        String businessOwner,
        String businessOwnerEmail,
        String technicalOwner,
        String technicalOwnerEmail
) {}
