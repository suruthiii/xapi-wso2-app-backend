package com.example.apimauth.service;

import com.example.apimauth.dto.ApiImportResponse;
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
public class ApiService {

    private final WebClient webClient;

    public ApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public ApiImportResponse importOpenApi(String authHeader, MultipartFile file, String additionalProperties) {
        try {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

            // Add file part
            body.add("file", new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            });

            // Add additional properties part
            body.add("additionalProperties", additionalProperties);

            return webClient.post()
                    .uri("/api/am/publisher/v4/apis/import-openapi")
                    .header(HttpHeaders.AUTHORIZATION, authHeader)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData(body))
                    .retrieve()
                    .onStatus(status -> !status.is2xxSuccessful(),
                            response -> response.bodyToMono(String.class)
                                    .flatMap(error -> Mono.error(new RuntimeException("API Import failed: " + error))))
                    .bodyToMono(ApiImportResponse.class)
                    .block();
        } catch (IOException e) {
            throw new RuntimeException("Error processing file upload", e);
        }
    }
}