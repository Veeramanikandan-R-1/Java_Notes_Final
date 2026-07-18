# Revision - Config Server - Centralized Config

## Core Recall

- Config Server centralizes environment-specific config.
- Clients fetch config by application name and profile.
- Git backend is good for reviewed non-secret values.
- Secrets should use Vault or secret manager.
- Validate config at startup.

## Production Checklist

- Use `@ConfigurationProperties`.
- Avoid plain secrets in Git.
- Review production config changes.
- Keep profile differences small.
- Decide startup behavior if config server is unavailable.

## Mistakes

- Storing passwords in YAML repo.
- Scattered `@Value` strings.
- Dynamic refresh for unsafe values.
- Business data in config.

## Cheat Sheet

| Value | Store In |
|---|---|
| Timeout | Config Server |
| Endpoint URL | Config Server |
| Password | Secret manager |
| Feature flag | Config/flag platform |
| Customer data | Database |

## Interview Answers

**Why centralized config?**  
It keeps service configuration consistent, auditable, and environment-specific.

**Why `@ConfigurationProperties`?**  
It provides structured binding and validation.

## Exercise

Create `order-service-dev.yml` and bind payment timeout values.
