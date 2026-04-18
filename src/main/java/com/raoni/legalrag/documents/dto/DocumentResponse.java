package com.raoni.legalrag.documents.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record DocumentResponse(
        UUID id,
        String title,
        String sourceType,
        Instant createdAt,
        List<DocumentChunkResponse> chunks
) {
}
