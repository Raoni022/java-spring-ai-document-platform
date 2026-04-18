package com.raoni.legalrag.generation.application;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OutputGuardrailServiceTest {
    private final OutputGuardrailService service = new OutputGuardrailService();

    @Test
    void warnsWhenOutputHasNoCitationsOrDisclaimer() {
        var warnings = service.validate("Generated draft", List.of());

        assertThat(warnings).contains("missing_citations", "missing_legal_disclaimer");
    }

    @Test
    void acceptsOutputWithDisclaimerAndCitations() {
        var warnings = service.validate("This is not legal advice. Draft based on context.", List.of("doc#chunk-0"));

        assertThat(warnings).isEmpty();
    }
}
