package com.raoni.legalrag.llmops.dto;

public record EvaluationResult(
        double groundednessScore,
        double citationCoverageScore,
        boolean safetyPassed,
        boolean fallbackUsed
) {
}
