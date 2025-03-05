package com.example.apimauth.controller;

import com.example.apimauth.dto.ManageAPI.*;
import com.example.apimauth.service.ApiService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/am/publisher/v4/apis")
public class ManageAPIController {

    private final ApiService apiService;

    public ManageAPIController(ApiService apiService) {
        this.apiService = apiService;
    }

    // ------------------------------------- Manage APIs -------------------------------------

    @PostMapping(value = "/import-openapi", consumes = "multipart/form-data")
    public ResponseEntity<?> importOpenApi(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestPart("file") MultipartFile file,
            @RequestPart("additionalProperties") String additionalProperties) {

        return ResponseEntity.ok(apiService.importOpenApi(authHeader, file, additionalProperties));
    }

    @GetMapping
    public ResponseEntity<ApiListResponse> getAllApis(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        ApiListResponse response = apiService.getAllApis(authHeader);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{apiId}")
    public ResponseEntity<ApiDetailResponse> getApiById(
            @PathVariable String apiId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        ApiDetailResponse response = apiService.getApiById(apiId, authHeader);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{apiId}")
    public ResponseEntity<Void> deleteApi(
            @PathVariable String apiId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        apiService.deleteApi(apiId, authHeader);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{apiId}/lifecycle-state")
    public ResponseEntity<LifecycleStateResponse> getLifecycleState(
            @PathVariable String apiId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        LifecycleStateResponse response = apiService.getLifecycleState(apiId, authHeader);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/change-lifecycle")
    public ResponseEntity<LifecycleUpdateResponse> updateLifecycleState(
            @RequestParam String apiId,
            @RequestParam String action,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        LifecycleUpdateResponse response = apiService.updateLifecycleState(apiId, action, authHeader);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{apiId}")
    public ResponseEntity<ApiDetailResponse> updateApi(
            @PathVariable String apiId,
            @RequestBody UpdateApiRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        ApiDetailResponse response = apiService.updateApi(apiId, request, authHeader);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{apiId}/swagger", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> updateApiSwagger(
            @PathVariable String apiId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestPart("apiDefinition") MultipartFile file) {

        Map<String, Object> response = apiService.updateApiSwagger(apiId, authHeader, file);
        return ResponseEntity.ok(response);
    }


    // ------------------------------------- Manage Revisions -------------------------------------

    @PostMapping("/{apiId}/revisions")
    public ResponseEntity<ApiRevisionResponse> createRevision(
            @PathVariable String apiId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody ApiRevisionRequest request) {

        ApiRevisionResponse response = apiService.createRevision(apiId, authHeader, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{apiId}/revisions")
    public ResponseEntity<RevisionsListResponse> getApiRevisions(
            @PathVariable String apiId,
            @RequestParam(required = false) String query,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        RevisionsListResponse response = apiService.getApiRevisions(apiId, query, authHeader);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{apiId}/revisions/{revisionId}")
    public ResponseEntity<RevisionsListResponse> deleteRevision(
            @PathVariable String apiId,
            @PathVariable String revisionId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        RevisionsListResponse response = apiService.deleteRevision(apiId, revisionId, authHeader);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{apiId}/deployments")
    public ResponseEntity<List<DeploymentInfo>> getApiDeployments(
            @PathVariable String apiId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        List<DeploymentInfo> response = apiService.getApiDeployments(apiId, authHeader);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{apiId}/deploy-revision")
    public ResponseEntity<List<DeploymentInfo>> deployRevision(
            @PathVariable String apiId,
            @RequestParam String revisionId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody List<DeploymentRequest> deploymentRequests) {

        List<DeploymentInfo> response = apiService.deployRevision(
                apiId,
                revisionId,
                authHeader,
                deploymentRequests
        );
        return ResponseEntity.ok(response);
    }

    // Add this endpoint to the controller
    @PostMapping("/{apiId}/undeploy-revision")
    public ResponseEntity<List<DeploymentInfo>> undeployRevision(
            @PathVariable String apiId,
            @RequestParam String revisionId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody List<DeploymentInfo> deploymentInfos) {

        List<DeploymentInfo> response = apiService.undeployRevision(
                apiId,
                revisionId,
                authHeader,
                deploymentInfos
        );
        return ResponseEntity.ok(response);
    }
}