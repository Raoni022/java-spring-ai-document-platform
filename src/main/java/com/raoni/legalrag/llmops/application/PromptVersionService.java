package com.raoni.legalrag.llmops.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PromptVersionService {
    private final String currentVersion;

    public PromptVersionService(@Value("${app.ai.prompt-version:legal-draft-v1}") String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String currentVersion() {
        return currentVersion;
    }
}
