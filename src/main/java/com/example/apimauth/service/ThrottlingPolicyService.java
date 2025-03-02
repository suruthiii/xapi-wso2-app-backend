package com.example.apimauth.service;

import com.example.apimauth.dto.ErrorResponse;
import com.example.apimauth.dto.StreamingThrottlingPolicyListResponse;
import com.example.apimauth.dto.ThrottlingPolicyDTO;
import com.example.apimauth.dto.ThrottlingPolicyListResponse;
import com.example.apimauth.exception.ApimIntegrationException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ThrottlingPolicyService {

    private final WebClient webClient;

    public ThrottlingPolicyService(WebClient webClient) {
        this.webClient = webClient;
    }

    public ThrottlingPolicyListResponse getPoliciesByLevel(String policyLevel, String authHeader) {
        return webClient.get()
                .uri("/api/am/publisher/v4/throttling-policies/{policyLevel}", policyLevel)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToMono(ThrottlingPolicyListResponse.class)
                .block();
    }

    public StreamingThrottlingPolicyListResponse getStreamingPolicies(
            int limit,
            int offset,
            String authHeader) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/am/publisher/v4/throttling-policies/streaming/subscription")
                        .queryParam("limit", limit)
                        .queryParam("offset", offset)
                        .build())
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToMono(StreamingThrottlingPolicyListResponse.class)
                .block();
    }

    public ThrottlingPolicyDTO getPolicyDetails(String policyLevel, String policyName, String authHeader) {
        return webClient.get()
                .uri("/api/am/publisher/v4/throttling-policies/{policyLevel}/{policyName}", policyLevel, policyName)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToMono(ThrottlingPolicyDTO.class)
                .block();
    }
}