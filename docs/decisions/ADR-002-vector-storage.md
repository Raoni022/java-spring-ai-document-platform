# ADR-002: PostgreSQL with pgvector production target

## Status

Accepted

## Context

The platform needs vector-search-ready storage while remaining easy to run locally.

## Decision

Use PostgreSQL as the primary database and document pgvector as the production vector extension. The current local retrieval implementation uses lexical scoring plus serialized deterministic embeddings to keep tests reproducible.

## Consequences

Benefits:

- simple local setup
- production path toward pgvector similarity search
- one database for metadata and vectors

Trade-offs:

- current search is not true semantic search
- pgvector indexes should be added in a production profile
