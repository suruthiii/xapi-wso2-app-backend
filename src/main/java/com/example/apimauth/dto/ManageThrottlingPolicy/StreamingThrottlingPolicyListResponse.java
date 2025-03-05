package com.example.apimauth.dto.ManageThrottlingPolicy;

import com.example.apimauth.dto.ManageAPI.PaginationDTO;

import java.util.List;

public record StreamingThrottlingPolicyListResponse(
        int count,
        List<StreamingThrottlingPolicyDTO> list,
        PaginationDTO pagination
) {}
