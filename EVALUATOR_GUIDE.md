# Evaluator Guide — Enterprise AI Document Platform

This guide is for recruiters, hiring managers, and technical evaluators reviewing this repository quickly.

## What this project demonstrates

This repository demonstrates a production-oriented architecture demo for enterprise AI document workflows using Java 21 and Spring Boot 3.

It focuses on:

- document ingestion
- semantic retrieval architecture
- RAG-style document drafting
- auditability
- safety checks around AI usage
- output validation
- prompt/versioning concepts
- backend maintainability
- enterprise deployment mapping

## Why it matters

Many AI demos are only a prompt wrapped in a UI. Enterprise AI systems require more structure:

- documents must be ingested and chunked
- retrieved context must be traceable
- generated output needs guardrails
- sensitive content may need redaction
- AI actions should be auditable
- prompts and outputs need evaluation hooks
- systems need clean API boundaries and tests

This project is designed to show those backend boundaries in a Java/Spring stack.

## Quick review path

1. Read `README.md` for the architecture and project intent.
2. Review `pom.xml` to confirm Java 21 / Spring Boot 3 dependencies.
3. Check the API/controller layer for the exposed document, retrieval, and generation flows.
4. Review service classes for safety checks, prompt handling, retrieval, and audit behavior.
5. Review tests to understand what behavior is verified.

## What is implemented vs. simplified

Implemented:

- Spring Boot API structure
- layered backend architecture
- validation-oriented API design
- document/retrieval/generation boundaries
- PostgreSQL-oriented persistence direction
- Flyway migration setup
- OpenAPI documentation
- testing foundation
- Docker/local development direction

Simplified for portfolio review:

- LLM provider is mocked/deterministic by default
- embedding behavior is local/deterministic for reproducibility
- production pgvector similarity indexing is represented as an upgrade path
- Azure components are mapped architecturally, not deployed

## Technical strengths to look for

- separation between ingestion, retrieval, generation, safety, and audit concerns
- explicit AI safety layer rather than hidden prompt-only behavior
- enterprise-friendly Java/Spring implementation
- discussion-ready architecture trade-offs
- local-first review without requiring paid AI provider keys

## Suggested interview discussion points

- how to move from deterministic embeddings to a real embedding provider
- how to implement pgvector similarity search in production
- how to evaluate generated output for groundedness and citation coverage
- where prompt injection checks should happen
- how to structure audit logs for AI actions
- how to split the modular monolith into services if scale required it
- how to deploy the architecture to Azure or another cloud provider

## Production upgrade path

- add real Spring AI or LangChain4j provider integration
- add pgvector indexes and similarity queries
- add authentication and authorization
- add rate limiting
- add real prompt registry
- add evaluation datasets
- add observability dashboards
- add cloud deployment manifests
