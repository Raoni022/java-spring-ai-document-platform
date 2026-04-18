# ADR-003: Explicit AI safety boundary

## Status

Accepted

## Context

AI-enabled applications need controls around prompt injection, sensitive data, output quality, and traceability.

## Decision

Create explicit AI safety services instead of hiding safety checks inside controllers or prompt templates.

## Consequences

Benefits:

- easier testing
- clearer security responsibility
- better interview discussion surface
- safer future integration with real LLM providers

Trade-offs:

- current implementation is rule-based
- production systems require stronger classifiers and policy enforcement
