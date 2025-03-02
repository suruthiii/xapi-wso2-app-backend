package com.example.apimauth.dto;

import java.util.List;

public record ThrottlingPolicyListResponse(
        int count,
        List<ThrottlingPolicyDTO> list,
        PaginationDTO pagination
) {}
