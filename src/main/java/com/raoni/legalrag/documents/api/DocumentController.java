package com.raoni.legalrag.documents.api;

import com.raoni.legalrag.documents.application.DocumentIngestionService;
import com.raoni.legalrag.documents.dto.DocumentResponse;
import com.raoni.legalrag.documents.dto.IngestDocumentRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    private final DocumentIngestionService service;

    public DocumentController(DocumentIngestionService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentResponse ingest(@Valid @RequestBody IngestDocumentRequest request) {
        return service.ingest(request);
    }
}
