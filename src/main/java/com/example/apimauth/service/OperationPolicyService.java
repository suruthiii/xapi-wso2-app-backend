package com.example.apimauth.service;

import com.example.apimauth.dto.ErrorResponse;
import com.example.apimauth.dto.PolicyDTO;
import com.example.apimauth.dto.PolicyListResponse;
import com.example.apimauth.exception.ApimIntegrationException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class OperationPolicyService {

    private final WebClient webClient;

    public OperationPolicyService(WebClient webClient) {
        this.webClient = webClient;
    }

    public PolicyListResponse getApiOperationPolicies(String apiId, String authHeader) {
        return webClient.get()
                .uri("/api/am/publisher/v4/apis/{apiId}/operation-policies", apiId)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToMono(PolicyListResponse.class)
                .block();
    }

    public PolicyDTO createApiSpecificPolicy(
            String apiId,
            String authHeader,
            MultipartFile policySpecFile,
            MultipartFile synapsePolicyDefinitionFile) {

        try {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

            body.add("policySpecFile", new ByteArrayResource(policySpecFile.getBytes()) {
                @Override public String getFilename() {
                    return policySpecFile.getOriginalFilename();
                }
            });

            body.add("synapsePolicyDefinitionFile", new ByteArrayResource(synapsePolicyDefinitionFile.getBytes()) {
                @Override public String getFilename() {
                    return synapsePolicyDefinitionFile.getOriginalFilename();
                }
            });

            return webClient.post()
                    .uri("/api/am/publisher/v4/apis/{apiId}/operation-policies", apiId)
                    .header(HttpHeaders.AUTHORIZATION, authHeader)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData(body))
                    .retrieve()
                    .onStatus(status -> !status.is2xxSuccessful(),
                            response -> response.bodyToMono(ErrorResponse.class)
                                    .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                    .bodyToMono(PolicyDTO.class)
                    .block();
        } catch (IOException e) {
            throw new RuntimeException("Error processing policy files", e);
        }
    }

    public PolicyDTO getPolicyDetails(String apiId, String policyId, String authHeader) {
        return webClient.get()
                .uri("/api/am/publisher/v4/apis/{apiId}/operation-policies/{policyId}", apiId, policyId)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToMono(PolicyDTO.class)
                .block();
    }

    public void deletePolicy(String apiId, String policyId, String authHeader) {
        webClient.delete()
                .uri("/api/am/publisher/v4/apis/{apiId}/operation-policies/{policyId}", apiId, policyId)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .toBodilessEntity()
                .block();
    }
}