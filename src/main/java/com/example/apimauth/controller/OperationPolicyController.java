package com.example.apimauth.controller;

import com.example.apimauth.dto.PolicyDTO;
import com.example.apimauth.dto.PolicyListResponse;
import com.example.apimauth.service.OperationPolicyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/am/publisher/v4/apis/{apiId}/operation-policies")
public class OperationPolicyController {

    private final OperationPolicyService policyService;

    public OperationPolicyController(OperationPolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping
    public ResponseEntity<PolicyListResponse> getOperationPolicies(
            @PathVariable String apiId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        PolicyListResponse response = policyService.getApiOperationPolicies(apiId, authHeader);
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PolicyDTO> createOperationPolicy(
            @PathVariable String apiId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestPart("policySpecFile") MultipartFile policySpecFile,
            @RequestPart("synapsePolicyDefinitionFile") MultipartFile synapsePolicyDefinitionFile) {

        PolicyDTO response = policyService.createApiSpecificPolicy(
                apiId,
                authHeader,
                policySpecFile,
                synapsePolicyDefinitionFile
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{operationPolicyId}")
    public ResponseEntity<PolicyDTO> getPolicyDetails(
            @PathVariable String apiId,
            @PathVariable String operationPolicyId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        PolicyDTO response = policyService.getPolicyDetails(apiId, operationPolicyId, authHeader);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{operationPolicyId}")
    public ResponseEntity<Void> deletePolicy(
            @PathVariable String apiId,
            @PathVariable String operationPolicyId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        policyService.deletePolicy(apiId, operationPolicyId, authHeader);
        return ResponseEntity.noContent().build();
    }
}