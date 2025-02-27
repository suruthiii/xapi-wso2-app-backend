package com.example.apimauth.dto;

import java.util.List;

public record RevisionsListResponse(
        int count,
        List<ApiRevisionResponse> list
) {}
