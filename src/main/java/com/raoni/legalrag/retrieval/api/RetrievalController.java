package com.raoni.legalrag.retrieval.api;

import com.raoni.legalrag.retrieval.application.RetrievalService;
import com.raoni.legalrag.retrieval.dto.RetrievalRequest;
import com.raoni.legalrag.retrieval.dto.RetrievalResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/retrieval")
public class RetrievalController {
    private final RetrievalService retrievalService;

    public RetrievalController(RetrievalService retrievalService) {
        this.retrievalService = retrievalService;
    }

    @PostMapping("/search")
    public RetrievalResponse search(@Valid @RequestBody RetrievalRequest request) {
        return retrievalService.search(request.query(), request.resolvedTopK());
    }
}
