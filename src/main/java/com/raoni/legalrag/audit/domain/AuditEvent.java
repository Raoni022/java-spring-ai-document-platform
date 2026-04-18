package com.raoni.legalrag.audit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "audit_events")
public class AuditEvent {
    @Id
    private UUID id;
    private String eventType;
    private String aggregateId;

    @Column(columnDefinition = "TEXT")
    private String details;

    private Instant createdAt;

    protected AuditEvent() {
    }

    public AuditEvent(String eventType, String aggregateId, String details) {
        this.id = UUID.randomUUID();
        this.eventType = eventType;
        this.aggregateId = aggregateId;
        this.details = details;
        this.createdAt = Instant.now();
    }

    public UUID getId() { return id; }
    public String getEventType() { return eventType; }
    public String getAggregateId() { return aggregateId; }
    public String getDetails() { return details; }
    public Instant getCreatedAt() { return createdAt; }
}
