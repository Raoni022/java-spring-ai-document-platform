package com.raoni.legalrag.generation.application;

import com.raoni.legalrag.audit.application.AuditService;
import com.raoni.legalrag.generation.dto.GenerateDraftRequest;
import com.raoni.legalrag.generation.dto.GenerateDraftResponse;
import com.raoni.legalrag.llmops.application.EvaluationService;
import com.raoni.legalrag.llmops.application.PromptVersionService;
import com.raoni.legalrag.retrieval.application.RetrievalService;
import com.raoni.legalrag.retrieval.dto.RetrievedChunk;
import com.raoni.legalrag.securityai.application.PromptInjectionDetector;
import com.raoni.legalrag.securityai.application.SensitiveDataRedactor;
import com.raoni.legalrag.securityai.dto.AiSafetyCheckResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LegalDraftGenerationService {
    private final RetrievalService retrievalService;
    private final PromptInjectionDetector injectionDetector;
    private final SensitiveDataRedactor redactor;
    private final PromptBuilder promptBuilder;
    private final OutputGuardrailService guardrailService;
    private final EvaluationService evaluationService;
    private final PromptVersionService promptVersionService;
    private final AuditService auditService;

    public LegalDraftGenerationService(
            RetrievalService retrievalService,
            PromptInjectionDetector injectionDetector,
            SensitiveDataRedactor redactor,
            PromptBuilder promptBuilder,
            OutputGuardrailService guardrailService,
            EvaluationService evaluationService,
            PromptVersionService promptVersionService,
            AuditService auditService
    ) {
        this.retrievalService = retrievalService;
        this.injectionDetector = injectionDetector;
        this.redactor = redactor;
        this.promptBuilder = promptBuilder;
        this.guardrailService = guardrailService;
        this.evaluationService = evaluationService;
        this.promptVersionService = promptVersionService;
        this.auditService = auditService;
    }

    @Transactional
    public GenerateDraftResponse generate(GenerateDraftRequest request) {
        String redactedSummary = redactor.redact(request.caseSummary());
        AiSafetyCheckResult safety = injectionDetector.check(redactedSummary);
        List<String> warnings = new ArrayList<>(safety.reasons());

        if (!safety.safe()) {
            String blockedDraft = "Request blocked by AI safety controls. This project is a technical demonstration and does not provide legal advice.";
            var evaluation = evaluationService.evaluate(blockedDraft, List.of(), false, true);
            auditService.record("GENERATION_BLOCKED", "n/a", String.join(",", warnings));
            return new GenerateDraftResponse(blockedDraft, List.of(), warnings, evaluation);
        }

        var retrieval = retrievalService.search(redactedSummary, request.resolvedTopK());
        List<RetrievedChunk> chunks = retrieval.results();
        List<String> citations = chunks.stream().map(RetrievedChunk::citationRef).toList();

        String prompt = promptBuilder.buildLegalDraftPrompt(
                redactedSummary,
                request.requestedDocumentType(),
                request.jurisdiction(),
                chunks,
                promptVersionService.currentVersion()
        );

        String draft = mockGenerateDraft(request, chunks, prompt.length());
        warnings.addAll(guardrailService.validate(draft, citations));
        var evaluation = evaluationService.evaluate(draft, citations, warnings.isEmpty(), chunks.isEmpty());
        auditService.record("LEGAL_DRAFT_GENERATED", request.requestedDocumentType(), "citations=" + citations.size() + ",warnings=" + warnings.size());
        return new GenerateDraftResponse(draft, citations, warnings, evaluation);
    }

    private String mockGenerateDraft(GenerateDraftRequest request, List<RetrievedChunk> chunks, int promptLength) {
        String references = chunks.isEmpty()
                ? "No retrieved context was available. Additional source material is required."
                : "Grounded references: " + String.join(", ", chunks.stream().map(RetrievedChunk::citationRef).toList()) + ".";

        return """
                Technical demonstration draft for %s in jurisdiction %s.

                This project is a technical demonstration and does not provide legal advice.

                Summary:
                Based on the provided case summary, prepare a structured document with facts, obligations, risk notes, and review points.

                Draft clause:
                The parties should define responsibilities, evidence, deadlines, confidentiality expectations, termination conditions, and dispute handling according to the applicable context.

                %s

                Operational note: generated with a mock LLM provider for reproducible local execution. Prompt size: %d characters.
                """.formatted(request.requestedDocumentType(), request.jurisdiction(), references, promptLength);
    }
}
