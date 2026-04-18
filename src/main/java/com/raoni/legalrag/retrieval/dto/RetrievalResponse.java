package com.raoni.legalrag.retrieval.dto;

import java.util.List;

public record RetrievalResponse(
        String query,
        List<RetrievedChunk> results
) {
}
