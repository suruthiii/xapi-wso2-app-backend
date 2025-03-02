package com.example.apimauth.controller;

import com.example.apimauth.dto.*;
import com.example.apimauth.service.GatewayPolicyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/am/publisher/v4/gateway-policies")
public class GatewayPolicyController {

    private final GatewayPolicyService policyService;

    public GatewayPolicyController(GatewayPolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping
    public ResponseEntity<GatewayPolicyListResponse> getAllGatewayPolicies(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        GatewayPolicyListResponse response = policyService.getAllGatewayPolicies(authHeader);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<GatewayPolicyDetailDTO> getGatewayPolicyById(
            @PathVariable String policyId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        GatewayPolicyDetailDTO response = policyService.getGatewayPolicyById(policyId, authHeader);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{policyId}")
    public ResponseEntity<Void> deleteGatewayPolicy(
            @PathVariable String policyId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        policyService.deleteGatewayPolicy(policyId, authHeader);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<GatewayPolicyResponse> engageGatewayPolicies(
            @RequestBody GatewayPolicyRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        GatewayPolicyResponse response = policyService.engageGatewayPolicies(request, authHeader);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{policyId}/deploy")
    public ResponseEntity<List<?>> deployGatewayPolicyMapping(
            @PathVariable String policyId,
            @RequestBody List<GatewayDeploymentRequest> deployments,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        List<?> response = policyService.deployGatewayPolicyMapping(policyId, deployments, authHeader);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{policyId}")
    public ResponseEntity<GatewayPolicyDetailDTO> updateGatewayPolicy(
            @PathVariable String policyId,
            @RequestBody GatewayPolicyRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        GatewayPolicyDetailDTO response = policyService.updateGatewayPolicy(policyId, request, authHeader);
        return ResponseEntity.ok(response);
    }
}