package com.raoni.legalrag.documents.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "document_chunks")
public class DocumentChunk {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_id")
    private Document document;

    private int chunkIndex;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String embedding;

    private String citationRef;
    private Instant createdAt;

    protected DocumentChunk() {
    }

    public DocumentChunk(int chunkIndex, String content, String embedding, String citationRef) {
        this.id = UUID.randomUUID();
        this.chunkIndex = chunkIndex;
        this.content = content;
        this.embedding = embedding;
        this.citationRef = citationRef;
        this.createdAt = Instant.now();
    }

    void attachTo(Document document) {
        this.document = document;
    }

    public UUID getId() { return id; }
    public Document getDocument() { return document; }
    public int getChunkIndex() { return chunkIndex; }
    public String getContent() { return content; }
    public String getEmbedding() { return embedding; }
    public String getCitationRef() { return citationRef; }
    public Instant getCreatedAt() { return createdAt; }
}
