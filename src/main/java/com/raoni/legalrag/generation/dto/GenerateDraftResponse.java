package com.raoni.legalrag.generation.dto;

import com.raoni.legalrag.llmops.dto.EvaluationResult;

import java.util.List;

public record GenerateDraftResponse(
        String draft,
        List<String> citations,
        List<String> safetyWarnings,
        EvaluationResult evaluation
) {
}
