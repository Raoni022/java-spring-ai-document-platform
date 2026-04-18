package com.raoni.legalrag.retrieval.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record RetrievalRequest(
        @NotBlank String query,
        @Min(1) @Max(10) Integer topK
) {
    public int resolvedTopK() {
        return topK == null ? 3 : topK;
    }
}
