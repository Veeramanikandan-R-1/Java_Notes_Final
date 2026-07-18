# Revision Notes

* SLF4J is logging facade.
* Logback is common logging implementation in Spring Boot.
* Use parameterized logging.
* Do not log secrets or tokens.
* Use appropriate levels: trace, debug, info, warn, error.
* Include correlation IDs for request tracing.
* Prefer structured logs in production.

---

# Cheat Sheet

| Level | Use |
| ----- | --- |
| DEBUG | Developer diagnostics |
| INFO | Normal important events |
| WARN | Recoverable issue |
| ERROR | Failure needing attention |
| SLF4J | Facade |
| Logback | Implementation |

---

# Interview Questions with Answers

### 1. What is SLF4J?

A logging facade that lets code use a common API independent of logging implementation.

### 2. Why parameterized logging?

It avoids unnecessary string creation and keeps logs structured.

### 3. What should not be logged?

Passwords, tokens, OTPs, secrets, and sensitive personal data.

