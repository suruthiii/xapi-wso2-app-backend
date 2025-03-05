package com.example.apimauth.dto.ManageGatewayPolicy;

import com.example.apimauth.dto.ManageAPI.GatewayPolicyDTO;
import com.example.apimauth.dto.ManageAPI.PaginationDTO;

import java.util.List;

public record GatewayPolicyListResponse(
        int count,
        List<GatewayPolicyDTO> list,
        PaginationDTO pagination
) {}
