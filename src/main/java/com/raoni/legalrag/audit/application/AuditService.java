package com.raoni.legalrag.audit.application;

import com.raoni.legalrag.audit.domain.AuditEvent;
import com.raoni.legalrag.audit.repository.AuditEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditService {
    private final AuditEventRepository repository;

    public AuditService(AuditEventRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void record(String eventType, String aggregateId, String details) {
        repository.save(new AuditEvent(eventType, aggregateId, details));
    }
}
