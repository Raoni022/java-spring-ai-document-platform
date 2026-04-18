package com.raoni.legalrag.messaging.application;

import com.raoni.legalrag.messaging.domain.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InMemoryDomainEventPublisher implements DomainEventPublisher {
    private static final Logger log = LoggerFactory.getLogger(InMemoryDomainEventPublisher.class);

    @Override
    public void publish(DomainEvent event) {
        log.info("Published domain event type={} aggregateId={} occurredAt={}",
                event.eventType(), event.aggregateId(), event.occurredAt());
    }
}
