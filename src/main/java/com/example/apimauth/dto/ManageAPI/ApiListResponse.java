package com.example.apimauth.dto.ManageAPI;

import java.util.List;

public record ApiListResponse(
        int count,
        List<ApiDTO> list,
        PaginationDTO pagination
) {}
