package com.example.apimauth.controller;

import com.example.apimauth.dto.StreamingThrottlingPolicyListResponse;
import com.example.apimauth.dto.ThrottlingPolicyDTO;
import com.example.apimauth.dto.ThrottlingPolicyListResponse;
import com.example.apimauth.service.ThrottlingPolicyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/am/publisher/v4/throttling-policies")
public class ThrottlingPolicyController {

    private final ThrottlingPolicyService policyService;

    public ThrottlingPolicyController(ThrottlingPolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping("/{policyLevel}")
    public ResponseEntity<ThrottlingPolicyListResponse> getPoliciesByLevel(
            @PathVariable String policyLevel,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        ThrottlingPolicyListResponse response = policyService.getPoliciesByLevel(policyLevel, authHeader);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/streaming/subscription")
    public ResponseEntity<StreamingThrottlingPolicyListResponse> getStreamingPolicies(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        StreamingThrottlingPolicyListResponse response = policyService.getStreamingPolicies(
                limit, offset, authHeader
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{policyLevel}/{policyName}")
    public ResponseEntity<ThrottlingPolicyDTO> getPolicyDetails(
            @PathVariable String policyLevel,
            @PathVariable String policyName,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        ThrottlingPolicyDTO response = policyService.getPolicyDetails(policyLevel, policyName, authHeader);
        return ResponseEntity.ok(response);
    }
}