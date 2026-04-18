package com.raoni.legalrag.securityai.dto;

import java.util.List;

public record AiSafetyCheckResult(
        boolean safe,
        List<String> reasons
) {
    public static AiSafetyCheckResult safe() {
        return new AiSafetyCheckResult(true, List.of());
    }

    public static AiSafetyCheckResult unsafe(List<String> reasons) {
        return new AiSafetyCheckResult(false, reasons);
    }
}
