package com.example.apimauth.dto;

public record PaginationDTO(
        int offset,
        int limit,
        int total,
        String next,
        String previous
) {}