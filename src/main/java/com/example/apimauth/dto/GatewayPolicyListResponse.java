package com.example.apimauth.dto;

import java.util.List;

public record GatewayPolicyListResponse(
        int count,
        List<GatewayPolicyDTO> list,
        PaginationDTO pagination
) {}
