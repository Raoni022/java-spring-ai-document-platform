# ADR-001: Modular monolith first

## Status

Accepted

## Context

The target role values microservices, scalability, automation, and architectural judgment. A portfolio project should demonstrate those boundaries without making local execution unnecessarily complex.

## Decision

Use a modular monolith with explicit package boundaries for documents, retrieval, generation, AI security, LLMOps, audit, and messaging.

## Consequences

Benefits:

- easier local setup
- easier code review
- clear service boundaries
- lower operational complexity

Trade-offs:

- not independently deployable yet
- eventing is local-first
- service-level scaling is a future step
