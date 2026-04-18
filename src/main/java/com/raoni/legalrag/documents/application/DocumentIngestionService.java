package com.raoni.legalrag.documents.application;

import com.raoni.legalrag.audit.application.AuditService;
import com.raoni.legalrag.documents.domain.Document;
import com.raoni.legalrag.documents.domain.DocumentChunk;
import com.raoni.legalrag.documents.dto.DocumentChunkResponse;
import com.raoni.legalrag.documents.dto.DocumentResponse;
import com.raoni.legalrag.documents.dto.IngestDocumentRequest;
import com.raoni.legalrag.documents.repository.DocumentRepository;
import com.raoni.legalrag.embeddings.application.EmbeddingService;
import com.raoni.legalrag.messaging.application.DomainEventPublisher;
import com.raoni.legalrag.messaging.domain.DocumentIngestedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Service
public class DocumentIngestionService {
    private static final int CHUNK_SIZE = 420;
    private static final int CHUNK_OVERLAP = 60;

    private final DocumentRepository documentRepository;
    private final EmbeddingService embeddingService;
    private final AuditService auditService;
    private final DomainEventPublisher eventPublisher;

    public DocumentIngestionService(
            DocumentRepository documentRepository,
            EmbeddingService embeddingService,
            AuditService auditService,
            DomainEventPublisher eventPublisher
    ) {
        this.documentRepository = documentRepository;
        this.embeddingService = embeddingService;
        this.auditService = auditService;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public DocumentResponse ingest(IngestDocumentRequest request) {
        Document document = new Document(
                request.title(),
                request.sourceType(),
                request.content(),
                serializeMetadata(request.metadata())
        );

        List<String> chunks = chunk(request.content());
        for (int i = 0; i < chunks.size(); i++) {
            String chunk = chunks.get(i);
            String embedding = embeddingService.serialize(embeddingService.embed(chunk));
            String citation = document.getTitle().replaceAll("\\s+", "-").toLowerCase() + "#chunk-" + i;
            document.addChunk(new DocumentChunk(i, chunk, embedding, citation));
        }

        Document saved = documentRepository.save(document);
        auditService.record("DOCUMENT_INGESTED", saved.getId().toString(), "chunks=" + saved.getChunks().size());
        eventPublisher.publish(new DocumentIngestedEvent(saved.getId(), saved.getChunks().size()));
        return toResponse(saved);
    }

    private List<String> chunk(String content) {
        String normalized = content.trim().replaceAll("\\s+", " ");
        List<String> chunks = new ArrayList<>();
        int start = 0;
        while (start < normalized.length()) {
            int end = Math.min(start + CHUNK_SIZE, normalized.length());
            chunks.add(normalized.substring(start, end));
            if (end == normalized.length()) {
                break;
            }
            start = Math.max(0, end - CHUNK_OVERLAP);
        }
        return chunks;
    }

    private String serializeMetadata(Map<String, String> metadata) {
        if (metadata == null || metadata.isEmpty()) {
            return "{}";
        }
        StringJoiner joiner = new StringJoiner(",", "{", "}");
        metadata.forEach((key, value) -> joiner.add("\"" + key + "\":\"" + value + "\""));
        return joiner.toString();
    }

    private DocumentResponse toResponse(Document document) {
        List<DocumentChunkResponse> chunks = document.getChunks().stream()
                .map(chunk -> new DocumentChunkResponse(chunk.getId(), chunk.getChunkIndex(), chunk.getContent(), chunk.getCitationRef()))
                .toList();
        return new DocumentResponse(document.getId(), document.getTitle(), document.getSourceType(), document.getCreatedAt(), chunks);
    }
}
