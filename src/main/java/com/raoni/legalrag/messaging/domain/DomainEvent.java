package com.raoni.legalrag.messaging.domain;

import java.time.Instant;

public interface DomainEvent {
    String eventType();
    String aggregateId();
    Instant occurredAt();
}
