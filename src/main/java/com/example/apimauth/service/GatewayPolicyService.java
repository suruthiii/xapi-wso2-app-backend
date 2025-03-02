package com.example.apimauth.service;

import com.example.apimauth.dto.*;
import com.example.apimauth.exception.ApimIntegrationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GatewayPolicyService {

    private final WebClient webClient;

    public GatewayPolicyService(WebClient webClient) {
        this.webClient = webClient;
    }

    public GatewayPolicyListResponse getAllGatewayPolicies(String authHeader) {
        return webClient.get()
                .uri("/api/am/publisher/v4/gateway-policies")
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToMono(GatewayPolicyListResponse.class)
                .block();
    }

    public GatewayPolicyDetailDTO getGatewayPolicyById(String policyId, String authHeader) {
        return webClient.get()
                .uri("/api/am/publisher/v4/gateway-policies/{policyId}", policyId)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToMono(GatewayPolicyDetailDTO.class)
                .block();
    }

    public void deleteGatewayPolicy(String policyId, String authHeader) {
        webClient.delete()
                .uri("/api/am/publisher/v4/gateway-policies/{policyId}", policyId)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .toBodilessEntity()
                .block();
    }

    public GatewayPolicyResponse engageGatewayPolicies(GatewayPolicyRequest request, String authHeader) {
        return webClient.post()
                .uri("/api/am/publisher/v4/gateway-policies")
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToMono(GatewayPolicyResponse.class)
                .block();
    }
}