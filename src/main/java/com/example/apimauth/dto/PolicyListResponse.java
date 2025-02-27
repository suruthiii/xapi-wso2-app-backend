package com.example.apimauth.dto;

import java.util.List;

public record PolicyListResponse(
        int count,
        List<PolicyDTO> list,
        PaginationDTO pagination
) {}
