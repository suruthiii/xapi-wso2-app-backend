package com.example.apimauth.dto.ManageOperationPolicy;

import java.util.List;

public record PolicyAttribute(
        String name,
        String displayName,
        String description,
        String validationRegex,
        String type,
        boolean required,
        Object defaultValue,
        List<String> allowedValues
) {}