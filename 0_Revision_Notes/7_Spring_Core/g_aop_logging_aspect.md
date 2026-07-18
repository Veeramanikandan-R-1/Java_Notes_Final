# Revision Notes

* AOP handles cross-cutting concerns.
* Common concerns: logging, metrics, security, transactions.
* Aspect contains cross-cutting logic.
* Advice is code executed at join points.
* Pointcut selects where advice applies.
* Spring AOP is proxy-based.
* Avoid heavy business logic in aspects.

---

# Cheat Sheet

| Concept | Meaning |
| ------- | ------- |
| Aspect | Cross-cutting module |
| Advice | Code to run |
| Pointcut | Matching rule |
| Join point | Method execution point |
| `@Around` | Wrap method |
| `@Before` | Before method |

---

# Interview Questions with Answers

### 1. What is AOP?

Aspect-Oriented Programming separates cross-cutting concerns from business logic.

### 2. Where use logging aspect?

For consistent method entry/exit, timing, or audit logs.

### 3. Limitation of Spring AOP?

It is proxy-based and mainly applies to Spring bean method calls.

