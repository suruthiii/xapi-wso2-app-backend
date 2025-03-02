package com.example.apimauth.dto;

import java.util.List;

public record StreamingThrottlingPolicyListResponse(
        int count,
        List<StreamingThrottlingPolicyDTO> list,
        PaginationDTO pagination
) {}
