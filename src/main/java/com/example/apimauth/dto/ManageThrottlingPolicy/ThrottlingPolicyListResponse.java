package com.example.apimauth.dto.ManageThrottlingPolicy;

import com.example.apimauth.dto.ManageAPI.PaginationDTO;

import java.util.List;

public record ThrottlingPolicyListResponse(
        int count,
        List<ThrottlingPolicyDTO> list,
        PaginationDTO pagination
) {}
