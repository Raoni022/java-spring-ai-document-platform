package com.raoni.legalrag.documents.dto;

import java.util.UUID;

public record DocumentChunkResponse(
        UUID id,
        int chunkIndex,
        String content,
        String citationRef
) {
}
