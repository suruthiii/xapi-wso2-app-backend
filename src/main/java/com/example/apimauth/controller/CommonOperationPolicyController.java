package com.example.apimauth.controller;

import com.example.apimauth.dto.PolicyDTO;
import com.example.apimauth.dto.PolicyListResponse;
import com.example.apimauth.service.CommonOperationPolicyService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/am/publisher/v4/operation-policies")
public class CommonOperationPolicyController {

    private final CommonOperationPolicyService policyService;

    public CommonOperationPolicyController(CommonOperationPolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping
    public ResponseEntity<PolicyListResponse> getAllCommonOperationPolicies(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        PolicyListResponse response = policyService.getAllCommonOperationPolicies(authHeader);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{policyId}/content")
    public ResponseEntity<ByteArrayResource> downloadPolicyContent(
            @PathVariable String policyId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        byte[] content = policyService.downloadPolicyContent(policyId, authHeader);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + policyId + ".xml\"")
                .body(new ByteArrayResource(content));
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<PolicyDTO> getCommonOperationPolicyById(
            @PathVariable String policyId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        PolicyDTO response = policyService.getCommonOperationPolicyById(policyId, authHeader);
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PolicyDTO> createCommonOperationPolicy(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestPart("policySpecFile") MultipartFile policySpecFile,
            @RequestPart("synapsePolicyDefinitionFile") MultipartFile synapsePolicyDefinitionFile) {

        PolicyDTO response = policyService.createCommonOperationPolicy(
                authHeader,
                policySpecFile,
                synapsePolicyDefinitionFile
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{policyId}")
    public ResponseEntity<Void> deleteCommonOperationPolicy(
            @PathVariable String policyId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        policyService.deleteCommonOperationPolicy(policyId, authHeader);
        return ResponseEntity.noContent().build();
    }
}