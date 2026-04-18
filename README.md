# Java Spring AI Document Platform

A production-oriented Java 21 and Spring Boot 3 platform for document ingestion, semantic retrieval, Retrieval-Augmented Generation (RAG), AI-assisted legal document drafting, auditability, and secure LLM integration.

> This project is a technical demonstration and does not provide legal advice.

## Why this project exists

Most AI demos stop at a chatbot or a single prompt. This project is structured as a backend platform that demonstrates how AI features can be integrated into a maintainable, testable, and production-oriented architecture.

The domain is legal document assistance because it requires grounded answers, traceability, safety checks, citations, audit logs, and careful handling of sensitive information.

## What this project demonstrates

- Java 21 backend development
- Spring Boot 3.x API design
- Layered architecture inspired by microservices
- Retrieval-Augmented Generation with document chunking and vector-search-ready storage
- PostgreSQL with pgvector production mapping
- Secure AI patterns, including prompt injection detection and output validation
- LLMOps concepts such as prompt versioning and evaluation scoring
- Event-driven design concepts inspired by Azure Service Bus
- CI/CD with GitHub Actions
- Docker-based local development
- Tests with JUnit 5 and Spring Boot Test

## Architecture

```text
client
  -> Spring Boot REST API
      -> document ingestion module
          -> chunking
          -> embedding generation
          -> document/chunk persistence
          -> domain event publishing
          -> audit logging
      -> retrieval module
          -> query embedding
          -> chunk scoring
          -> citation references
      -> generation module
          -> safety checks
          -> context retrieval
          -> prompt building
          -> mock LLM generation
          -> output guardrails
          -> evaluation scoring
          -> audit logging
```

The current implementation is a modular monolith. The package boundaries are intentionally designed so the system can later be split into independent services.

## Tech stack

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Validation
- PostgreSQL
- Flyway
- Springdoc OpenAPI
- Docker Compose
- GitHub Actions
- JUnit 5

## Main endpoints

### Health

```http
GET /actuator/health
```

### Ingest document

```http
POST /api/documents
```

### Search relevant chunks

```http
POST /api/retrieval/search
```

### Generate legal draft

```http
POST /api/generation/legal-draft
```

## Quick start

### 1. Start PostgreSQL

```bash
docker compose up -d postgres
```

### 2. Run tests

```bash
mvn test
```

### 3. Run the application

```bash
mvn spring-boot:run
```

### 4. Open Swagger UI

```text
http://localhost:8080/swagger-ui.html
```

## Example requests

### Ingest a document

```bash
curl -X POST http://localhost:8080/api/documents \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Service Agreement Template",
    "sourceType": "template",
    "content": "This agreement defines responsibilities, payment terms, termination clauses, confidentiality duties, and dispute resolution procedures.",
    "metadata": {"jurisdiction":"BR", "category":"contract"}
  }'
```

### Retrieve context

```bash
curl -X POST http://localhost:8080/api/retrieval/search \
  -H "Content-Type: application/json" \
  -d '{
    "query": "termination and confidentiality obligations",
    "topK": 3
  }'
```

### Generate a grounded draft

```bash
curl -X POST http://localhost:8080/api/generation/legal-draft \
  -H "Content-Type: application/json" \
  -d '{
    "caseSummary": "A client needs a service agreement clause covering termination and confidentiality.",
    "requestedDocumentType": "contract clause",
    "jurisdiction": "BR",
    "topK": 3
  }'
```

## AI safety

The project includes a simple but explicit AI safety layer:

- prompt injection detection
- sensitive data redaction
- grounded prompt construction
- output validation
- citation requirement checks
- audit logging for important AI actions

These checks are intentionally transparent and easy to test. They are not presented as a complete security solution, but as an engineering baseline for safer AI integration.

## LLMOps

The project includes lightweight LLMOps concepts:

- prompt versioning
- generated output evaluation
- groundedness scoring
- citation coverage scoring
- fallback and safety status reporting

This demonstrates how AI behavior can be monitored and reasoned about beyond a single API call.

## Azure production mapping

This repository is local-first, but the architecture maps naturally to Azure:

| Local/project component | Azure production mapping |
|---|---|
| Spring Boot API | AKS or Azure App Service |
| Domain events | Azure Service Bus |
| Background processing | Azure Functions |
| PostgreSQL storage | Azure Database for PostgreSQL |
| Vector storage | PostgreSQL + pgvector |
| Secrets | Azure Key Vault |
| Observability | Azure Monitor / Application Insights |
| CI/CD | GitHub Actions |

This repository does not claim to be deployed to Azure. It demonstrates a deployment-ready direction and the architectural mapping.

## Trade-offs

- The default LLM implementation is mocked so the project runs without paid API keys.
- The embedding service is deterministic and local for reproducibility.
- Vector search is represented through vector-ready schema and local scoring; production deployment should use pgvector indexes.
- The system is currently a modular monolith to keep the repository easy to run and review.

## Future improvements

- Add real Spring AI provider integration
- Add pgvector similarity queries in production profile
- Add Azure Service Bus integration
- Add AKS deployment manifests
- Add evaluation datasets
- Add structured prompt registry
- Add rate limiting and authentication

## Author

Raoni Medeiros  
AI Automation & Systems Engineer
