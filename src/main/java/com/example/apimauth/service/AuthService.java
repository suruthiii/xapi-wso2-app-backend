package com.example.apimauth.service;

import com.example.apimauth.dto.Auth.ClientRegistrationRequest;
import com.example.apimauth.dto.Auth.ClientRegistrationResponse;
import com.example.apimauth.dto.Auth.TokenResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class AuthService {

    private static final String SCOPE = "openid apim:api_view apim:policies_import_export apim:api_create apim:api_delete apim:api_publish apim:api_manage apim:subscription_view apim:subscription_block apim:subscription_manage apim:threat_protection_policy_create apim:threat_protection_policy_manage apim:document_create apim:document_manage apim:api_mediation_policy_manage apim:mediation_policy_view apim:mediation_policy_create apim:mediation_policy_manage apim:common_operation_policy_view apim:common_operation_policy_manage apim:gateway_policy_view apim:gateway_policy_manage apim:client_certificates_view apim:client_certificates_add apim:client_certificates_update apim:client_certificates_manage apim:ep_certificates_view apim:ep_certificates_add apim:ep_certificates_update apim:ep_certificates_manage apim:publisher_settings apim:pub_alert_manage apim:shared_scope_manage apim:app_import_export apim:api_import_export apim:api_product_import_export apim:api_generate_key apim:admin apim:comment_view apim:comment_write apim:comment_manage apim:tier_view apim:tier_manage apim:api_list_view apim:api_definition_view apim:subscription_approval_view apim:subscription_approval_manage apim:llm_provider_read";

    private final WebClient webClient;

    public AuthService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getAccessToken(String username, String password) {
        ClientRegistrationResponse clientResponse = registerClient(username, password);
        TokenResponse tokenResponse = getToken(clientResponse.clientId(), clientResponse.clientSecret(), username, password);
        return tokenResponse.accessToken();
    }

    private ClientRegistrationResponse registerClient(String username, String password) {
        String encodedCredentials = Base64.getEncoder()
                .encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));

        ClientRegistrationRequest request = new ClientRegistrationRequest(
                "www.google.lk",
                "rest_api_publisher",
                username,
                "client_credentials password refresh_token",
                true
        );

        return webClient.post()
                .uri("/client-registration/v0.17/register")
                .header("Authorization", "Basic " + encodedCredentials)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(error -> Mono.error(new RuntimeException("Registration failed: " + error))))
                .bodyToMono(ClientRegistrationResponse.class)
                .block();
    }

    private TokenResponse getToken(String clientId, String clientSecret, String username, String password) {
        String encodedCredentials = Base64.getEncoder()
                .encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "client_credentials");
        formData.add("username", username);
        formData.add("password", password);
        formData.add("scope", SCOPE);

        return webClient.post()
                .uri("/oauth2/token")
                .header("Authorization", "Basic " + encodedCredentials)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(error -> Mono.error(new RuntimeException("Token failed: " + error))))
                .bodyToMono(TokenResponse.class)
                .block();
    }
}