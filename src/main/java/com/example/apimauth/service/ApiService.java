package com.example.apimauth.service;

import com.example.apimauth.dto.*;
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
import com.example.apimauth.exception.ApimIntegrationException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ApiService {

    private final WebClient webClient;

    public ApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public ApiImportResponse importOpenApi(String authHeader, MultipartFile file, String additionalProperties) {
        try {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new ByteArrayResource(file.getBytes()) {
                @Override public String getFilename() {
                    return file.getOriginalFilename();
                }
            });
            body.add("additionalProperties", additionalProperties);

            return webClient.post()
                    .uri("/api/am/publisher/v4/apis/import-openapi")
                    .header(HttpHeaders.AUTHORIZATION, authHeader)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData(body))
                    .retrieve()
                    .onStatus(status -> !status.is2xxSuccessful(),
                            response -> response.bodyToMono(ErrorResponse.class)
                                    .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                    .bodyToMono(ApiImportResponse.class)
                    .block();
        } catch (IOException e) {
            throw new RuntimeException("Error processing file upload", e);
        }
    }

    public ApiListResponse getAllApis(String authHeader) {
        return webClient.get()
                .uri("/api/am/publisher/v4/apis")
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToMono(ApiListResponse.class)
                .block();
    }

    public ApiDetailResponse getApiById(String apiId, String authHeader) {
        return webClient.get()
                .uri("/api/am/publisher/v4/apis/{apiId}", apiId)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToMono(ApiDetailResponse.class)
                .block();
    }

    public void deleteApi(String apiId, String authHeader) {
        webClient.delete()
                .uri("/api/am/publisher/v4/apis/{apiId}", apiId)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .toBodilessEntity()
                .block();
    }


    // ------------------------------------- Manage Revisions -------------------------------------

    public ApiRevisionResponse createRevision(String apiId, String authHeader, ApiRevisionRequest request) {
        return webClient.post()
                .uri("/api/am/publisher/v4/apis/{apiId}/revisions", apiId)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToMono(ApiRevisionResponse.class)
                .block();
    }

    public RevisionsListResponse getApiRevisions(String apiId, String query, String authHeader) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/am/publisher/v4/apis/{apiId}/revisions")
                        .queryParamIfPresent("query", Optional.ofNullable(query))
                        .build(apiId))
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToMono(RevisionsListResponse.class)
                .block();
    }

    public RevisionsListResponse deleteRevision(String apiId, String revisionId, String authHeader) {
        return webClient.delete()
                .uri("/api/am/publisher/v4/apis/{apiId}/revisions/{revisionId}", apiId, revisionId)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToMono(RevisionsListResponse.class)
                .block();
    }

    public List<DeploymentInfo> getApiDeployments(String apiId, String authHeader) {
        return webClient.get()
                .uri("/api/am/publisher/v4/apis/{apiId}/deployments", apiId)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToFlux(DeploymentInfo.class)
                .collectList()
                .block();
    }

    public List<DeploymentInfo> deployRevision(String apiId,
                                               String revisionId,
                                               String authHeader,
                                               List<DeploymentRequest> deploymentRequests) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/am/publisher/v4/apis/{apiId}/deploy-revision")
                        .queryParam("revisionId", revisionId)
                        .build(apiId))
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(deploymentRequests)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToFlux(DeploymentInfo.class)
                .collectList()
                .block();
    }

    // Add this method to ApiService
    public List<DeploymentInfo> undeployRevision(String apiId,
                                                 String revisionId,
                                                 String authHeader,
                                                 List<DeploymentInfo> deploymentInfos) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/am/publisher/v4/apis/{apiId}/undeploy-revision")
                        .queryParam("revisionId", revisionId)
                        .build(apiId))
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(deploymentInfos)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(error -> Mono.error(new ApimIntegrationException(error, response.rawStatusCode()))))
                .bodyToFlux(DeploymentInfo.class)
                .collectList()
                .block();
    }
}