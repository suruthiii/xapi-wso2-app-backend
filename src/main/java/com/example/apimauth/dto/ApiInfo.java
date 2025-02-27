package com.example.apimauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiInfo(@JsonProperty("id") String apiId) {}
