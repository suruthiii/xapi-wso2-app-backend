package com.example.apimauth.controller;

import com.example.apimauth.dto.GatewayPolicyDetailDTO;
import com.example.apimauth.dto.GatewayPolicyListResponse;
import com.example.apimauth.dto.GatewayPolicyRequest;
import com.example.apimauth.dto.GatewayPolicyResponse;
import com.example.apimauth.service.GatewayPolicyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}