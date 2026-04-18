package com.raoni.legalrag.retrieval.dto;

import java.util.UUID;

public record RetrievedChunk(
        UUID documentId,
        UUID chunkId,
        String documentTitle,
        String content,
        String citationRef,
        double relevanceScore
) {
}
