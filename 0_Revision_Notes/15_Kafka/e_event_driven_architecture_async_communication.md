# Revision - Event Driven Architecture - Async Communication

## Core Recall

- Event is an immutable fact.
- Command asks for action; event says something happened.
- Async communication reduces direct service coupling.
- Eventual consistency is normal.
- Consumers must be idempotent.

## Production Checklist

- Past-tense event names.
- Event ID, aggregate ID, timestamp, version.
- Backward-compatible schema.
- Outbox for reliable publish.
- Tracing and correlation IDs.

## Mistakes

- Vague `SomethingUpdated` events.
- No versioning.
- Treating events like mutable state.
- Async when immediate response is required.

## Cheat Sheet

| Pattern | Use |
|---|---|
| REST | Immediate response |
| Event | Notify that something happened |
| Choreography | Simple decentralized reactions |
| Orchestration | Complex workflow control |
| Outbox | Reliable event publishing |

## Interview Answers

**What is eventual consistency?**  
Services converge over time through events instead of one distributed transaction.

**Why event IDs?**  
For duplicate detection, tracing, and replay safety.

## Exercise

Design checkout events and define which services consume each event.
