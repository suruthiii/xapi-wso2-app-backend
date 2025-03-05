package com.example.apimauth.dto.ManageOperationPolicy;

import com.example.apimauth.dto.ManageAPI.PaginationDTO;

import java.util.List;

public record PolicyListResponse(
        int count,
        List<PolicyDTO> list,
        PaginationDTO pagination
) {}
