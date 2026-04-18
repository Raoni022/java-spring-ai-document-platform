CREATE TABLE documents (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    source_type VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    metadata_json TEXT,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE document_chunks (
    id UUID PRIMARY KEY,
    document_id UUID NOT NULL REFERENCES documents(id),
    chunk_index INTEGER NOT NULL,
    content TEXT NOT NULL,
    embedding TEXT NOT NULL,
    citation_ref VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE audit_events (
    id UUID PRIMARY KEY,
    event_type VARCHAR(120) NOT NULL,
    aggregate_id VARCHAR(120),
    details TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_document_chunks_document_id ON document_chunks(document_id);
CREATE INDEX idx_audit_events_event_type ON audit_events(event_type);
