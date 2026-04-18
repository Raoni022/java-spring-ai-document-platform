package com.raoni.legalrag.generation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record GenerateDraftRequest(
        @NotBlank String caseSummary,
        @NotBlank String requestedDocumentType,
        @NotBlank String jurisdiction,
        @Min(1) @Max(10) Integer topK
) {
    public int resolvedTopK() {
        return topK == null ? 3 : topK;
    }
}
