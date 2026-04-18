package com.raoni.legalrag.securityai.application;

import org.springframework.stereotype.Service;

@Service
public class SensitiveDataRedactor {
    public String redact(String input) {
        if (input == null) {
            return "";
        }
        return input
                .replaceAll("(?i)(api[_-]?key|token|secret|password)\\s*[:=]\\s*\\S+", "$1=[REDACTED]")
                .replaceAll("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}", "[EMAIL_REDACTED]");
    }
}
