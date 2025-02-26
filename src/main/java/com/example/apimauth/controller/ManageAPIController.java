package com.example.apimauth.controller;

import com.example.apimauth.dto.ApiDetailResponse;
import com.example.apimauth.dto.ApiImportResponse;
import com.example.apimauth.dto.ApiListResponse;
import com.example.apimauth.service.ApiService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
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

    @GetMapping
    public ResponseEntity<ApiListResponse> getAllApis(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        ApiListResponse response = apiService.getAllApis(authHeader);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{apiId}")
    public ResponseEntity<ApiDetailResponse> getApiById(
            @PathVariable String apiId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        ApiDetailResponse response = apiService.getApiById(apiId, authHeader);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{apiId}")
    public ResponseEntity<Void> deleteApi(
            @PathVariable String apiId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        apiService.deleteApi(apiId, authHeader);
        return ResponseEntity.noContent().build();
    }
}