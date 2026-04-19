package com.raoni.legalrag.securityai.application;

import com.raoni.legalrag.securityai.dto.AiSafetyCheckResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class PromptInjectionDetector {
    private static final Map<String, String> RULES = Map.of(
            "ignore previous instructions", "attempt to override previous instructions",
            "reveal system prompt", "attempt to reveal hidden system prompt",
            "bypass security", "attempt to bypass security controls",
            "exfiltrate secrets", "attempt to exfiltrate secrets",
            "disable validation", "attempt to disable validation",
            "print environment variables", "attempt to expose environment variables"
    );

    public AiSafetyCheckResult check(String input) {
        String normalized = input == null ? "" : input.toLowerCase(Locale.ROOT);
        List<String> reasons = new ArrayList<>();
        RULES.forEach((pattern, reason) -> {
            if (normalized.contains(pattern)) {
                reasons.add(reason);
            }
        });
        return reasons.isEmpty() ? AiSafetyCheckResult.allowed() : AiSafetyCheckResult.unsafe(reasons);
    }
}
