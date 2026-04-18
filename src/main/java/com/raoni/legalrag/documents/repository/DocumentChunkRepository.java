package com.raoni.legalrag.documents.repository;

import com.raoni.legalrag.documents.domain.DocumentChunk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentChunkRepository extends JpaRepository<DocumentChunk, UUID> {
}
