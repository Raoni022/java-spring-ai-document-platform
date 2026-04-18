package com.raoni.legalrag.messaging.domain;

import java.time.Instant;
import java.util.UUID;

public record DocumentIngestedEvent(UUID documentId, int chunksCreated, Instant occurredAt) implements DomainEvent {
    public DocumentIngestedEvent(UUID documentId, int chunksCreated) {
        this(documentId, chunksCreated, Instant.now());
    }

    @Override
    public String eventType() {
        return "document.ingested";
    }

    @Override
    public String aggregateId() {
        return documentId.toString();
    }
}
