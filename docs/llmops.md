# LLMOps

This project includes lightweight LLMOps concepts to show how AI behavior can be evaluated and operated beyond a single prompt call.

## Included concepts

- Prompt versioning through `PromptVersionService`
- Grounded prompt construction with retrieved context
- Citation tracking from retrieved chunks
- Output validation through guardrails
- Evaluation scoring for generated drafts
- Audit events for generation outcomes

## Evaluation dimensions

The current evaluation service reports:

- groundedness score
- citation coverage score
- safety status
- fallback usage

These scores are intentionally simple. Their purpose is to make AI behavior visible and testable.

## Production next steps

A production-grade LLMOps layer should include:

- offline evaluation datasets
- regression tests for prompts
- human review feedback loops
- prompt registry
- model/version metadata
- hallucination checks
- latency and cost tracking
- per-tenant evaluation dashboards
