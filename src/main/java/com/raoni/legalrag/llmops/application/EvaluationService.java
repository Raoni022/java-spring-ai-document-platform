package com.raoni.legalrag.llmops.application;

import com.raoni.legalrag.llmops.dto.EvaluationResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {
    public EvaluationResult evaluate(String draft, List<String> citations, boolean safetyPassed, boolean fallbackUsed) {
        double groundedness = citations == null || citations.isEmpty() ? 0.35 : 0.85;
        double citationCoverage = citations == null || citations.isEmpty() ? 0.0 : Math.min(1.0, citations.size() / 3.0);
        if (draft == null || draft.isBlank()) {
            groundedness = 0.0;
        }
        return new EvaluationResult(round(groundedness), round(citationCoverage), safetyPassed, fallbackUsed);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
