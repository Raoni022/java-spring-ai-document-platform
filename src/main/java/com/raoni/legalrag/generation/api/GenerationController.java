package com.raoni.legalrag.generation.api;

import com.raoni.legalrag.generation.application.LegalDraftGenerationService;
import com.raoni.legalrag.generation.dto.GenerateDraftRequest;
import com.raoni.legalrag.generation.dto.GenerateDraftResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/generation")
public class GenerationController {
    private final LegalDraftGenerationService service;

    public GenerationController(LegalDraftGenerationService service) {
        this.service = service;
    }

    @PostMapping("/legal-draft")
    public GenerateDraftResponse generate(@Valid @RequestBody GenerateDraftRequest request) {
        return service.generate(request);
    }
}
