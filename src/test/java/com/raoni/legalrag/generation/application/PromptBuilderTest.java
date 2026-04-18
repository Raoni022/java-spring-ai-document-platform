package com.raoni.legalrag.generation.application;

import com.raoni.legalrag.retrieval.dto.RetrievedChunk;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PromptBuilderTest {
    private final PromptBuilder builder = new PromptBuilder();

    @Test
    void buildsGroundedPromptWithContextAndVersion() {
        var chunk = new RetrievedChunk(UUID.randomUUID(), UUID.randomUUID(), "Template", "Confidentiality clause", "template#chunk-0", 0.9);

        String prompt = builder.buildLegalDraftPrompt(
                "Need confidentiality clause",
                "contract clause",
                "BR",
                List.of(chunk),
                "legal-draft-v1"
        );

        assertThat(prompt).contains("legal-draft-v1");
        assertThat(prompt).contains("template#chunk-0");
        assertThat(prompt).contains("Do not provide legal advice");
    }
}
