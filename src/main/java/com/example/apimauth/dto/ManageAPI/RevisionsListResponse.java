package com.example.apimauth.dto.ManageAPI;

import java.util.List;

public record RevisionsListResponse(
        int count,
        List<ApiRevisionResponse> list
) {}
