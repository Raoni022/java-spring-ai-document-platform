package com.raoni.legalrag.documents.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Map;

public record IngestDocumentRequest(
        @NotBlank @Size(max = 255) String title,
        @NotBlank @Size(max = 100) String sourceType,
        @NotBlank @Size(min = 20) String content,
        Map<String, String> metadata
) {
}
