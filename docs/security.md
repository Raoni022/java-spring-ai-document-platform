# AI Security Notes

This repository demonstrates basic AI safety controls for backend applications that integrate with LLMs.

## Controls included

- Prompt injection detection
- Sensitive data redaction
- Grounded prompt construction
- Citation requirement checks
- Output leakage checks
- Audit events for generation and blocked requests

## Prompt injection examples detected

- ignore previous instructions
- reveal system prompt
- bypass security
- exfiltrate secrets
- disable validation
- print environment variables

## Important limitations

The current detector is rule-based and intentionally simple. It is useful for demonstrating the security boundary and testing strategy, but it is not sufficient for production by itself.

A production system should add:

- model-assisted classification
- allow/deny tool policies
- structured output schemas
- tenant isolation
- rate limiting
- authentication and authorization
- secrets management with Azure Key Vault or equivalent
- monitoring and alerting for suspicious prompts
