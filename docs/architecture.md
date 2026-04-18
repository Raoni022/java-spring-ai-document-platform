# Architecture

This project uses a modular monolith architecture designed around explicit package boundaries. The goal is to keep the local project easy to run while demonstrating service boundaries that can later become independently deployable microservices.

## Modules

```text
documents   -> ingestion, chunking, document persistence
embeddings  -> local deterministic embedding abstraction
retrieval   -> chunk search and citation references
generation  -> prompt construction, mock LLM response, output guardrails
securityai  -> prompt injection detection and sensitive data redaction
llmops      -> prompt versioning and output evaluation
audit       -> audit event persistence
messaging   -> domain event abstraction
```

## Request flow

```text
POST /api/documents
  -> validate payload
  -> create document
  -> split into chunks
  -> generate deterministic local embeddings
  -> persist document and chunks
  -> publish DocumentIngestedEvent
  -> record audit event

POST /api/generation/legal-draft
  -> redact sensitive data
  -> run prompt injection checks
  -> retrieve relevant chunks
  -> build grounded prompt
  -> generate draft with mock provider
  -> validate output guardrails
  -> evaluate generated answer
  -> record audit event
```

## Why modular monolith first

A microservice architecture would be reasonable for a production system, but premature service extraction makes a portfolio project harder to run and review. This repository keeps module boundaries clear while avoiding unnecessary distributed-system complexity.

## Production evolution

The modules can evolve into services:

- document ingestion service
- retrieval service
- generation service
- audit service
- background embedding workers

Domain events currently use an in-memory publisher. In production, this boundary can be replaced by Azure Service Bus.
