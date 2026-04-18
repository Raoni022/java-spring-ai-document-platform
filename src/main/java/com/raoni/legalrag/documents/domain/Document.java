package com.raoni.legalrag.documents.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "documents")
public class Document {
    @Id
    private UUID id;

    private String title;
    private String sourceType;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "metadata_json", columnDefinition = "TEXT")
    private String metadataJson;

    private Instant createdAt;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentChunk> chunks = new ArrayList<>();

    protected Document() {
    }

    public Document(String title, String sourceType, String content, String metadataJson) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.sourceType = sourceType;
        this.content = content;
        this.metadataJson = metadataJson;
        this.createdAt = Instant.now();
    }

    public void addChunk(DocumentChunk chunk) {
        chunks.add(chunk);
        chunk.attachTo(this);
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getSourceType() { return sourceType; }
    public String getContent() { return content; }
    public String getMetadataJson() { return metadataJson; }
    public Instant getCreatedAt() { return createdAt; }
    public List<DocumentChunk> getChunks() { return chunks; }
}
