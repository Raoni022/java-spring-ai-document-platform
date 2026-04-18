package com.raoni.legalrag.documents.repository;

import com.raoni.legalrag.documents.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
}
