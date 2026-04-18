package com.raoni.legalrag.messaging.application;

import com.raoni.legalrag.messaging.domain.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
