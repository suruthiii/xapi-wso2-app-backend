package com.example.apimauth.dto.ManageAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiInfo(@JsonProperty("id") String apiId) {}
