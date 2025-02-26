package com.example.apimauth.controller;

import com.example.apimauth.dto.ApiImportResponse;
import com.example.apimauth.service.ApiService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/am/publisher/v4/apis")
public class ManageAPIController {

    private final ApiService apiService;

    public ManageAPIController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping(value = "/import-openapi", consumes = "multipart/form-data")
    public ResponseEntity<ApiImportResponse> importOpenApi(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestPart("file") MultipartFile file,
            @RequestPart("additionalProperties") String additionalProperties) {

        ApiImportResponse response = apiService.importOpenApi(
                authHeader,
                file,
                additionalProperties
        );

        return ResponseEntity.ok(response);
    }
}