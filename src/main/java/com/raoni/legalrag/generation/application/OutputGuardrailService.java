package com.raoni.legalrag.generation.application;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class OutputGuardrailService {
    public List<String> validate(String output, List<String> citations) {
        List<String> warnings = new ArrayList<>();
        String normalized = output == null ? "" : output.toLowerCase(Locale.ROOT);

        if (normalized.isBlank()) {
            warnings.add("empty_output");
        }
        if (citations == null || citations.isEmpty()) {
            warnings.add("missing_citations");
        }
        if (normalized.contains("system prompt") || normalized.contains("hidden instruction")) {
            warnings.add("possible_instruction_leakage");
        }
        if (!normalized.contains("not legal advice") && !normalized.contains("does not provide legal advice")) {
            warnings.add("missing_legal_disclaimer");
        }
        return warnings;
    }
}
