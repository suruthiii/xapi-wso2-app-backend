package com.example.apimauth.dto;

import java.util.List;

public record PolicyMappingDTO(
        List<Object> request,
        List<Object> response,
        List<Object> fault
) {}