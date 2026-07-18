# Spring Core

### Subtopic: AOP - Logging Aspect

---

# 1. Fundamentals

AOP means Aspect-Oriented Programming.

It separates cross-cutting concerns from business logic.

Common cross-cutting concerns:

* Logging
* Transactions
* Security
* Metrics
* Auditing

Instead of writing logging in every service method, you can place it in one aspect.

---

# 2. Core Concepts

## Aspect

An aspect is a class containing cross-cutting logic.

```java
@Aspect
@Component
class LoggingAspect {
}
```

---

## Advice

Advice is code that runs at a join point.

| Advice | Runs |
| ------ | ---- |
| `@Before` | Before method |
| `@AfterReturning` | After successful return |
| `@AfterThrowing` | After exception |
| `@Around` | Around method execution |

---

## Pointcut

A pointcut selects where advice runs.

```java
@Around("execution(* com.example.service..*(..))")
```

This targets methods under the service package.

---

# 3. Internal Working

Spring AOP commonly uses proxies.

When another bean calls a proxied method, the proxy runs advice before or after delegating to the target object.

Important limitation:

Self-invocation does not usually go through the proxy. A method inside the same class calling another advised method in that class may skip the aspect.

---

# 4. Common Mistakes

* Logging sensitive data like passwords or tokens.
* Using broad pointcuts that affect too many methods.
* Swallowing exceptions inside `@Around` advice.
* Expecting aspects to run on private methods.
* Forgetting self-invocation proxy limitations.

---

# 5. Best Practices

* Keep pointcuts narrow.
* Log method name, duration, correlation ID, and result type.
* Avoid logging full request bodies with sensitive data.
* Let exceptions propagate unless intentionally handled.
* Use structured logs in production.

---

# 6. Code Example

```java
@Aspect
@Component
class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.order..*(..))")
    Object logDuration(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long duration = System.currentTimeMillis() - start;
            log.info("method={} durationMs={}", joinPoint.getSignature(), duration);
        }
    }
}
```

---

# 7. Real-world Scenarios

* API service execution time logging.
* Auditing admin operations.
* Adding metrics around repository calls.
* Central transaction management.
* Security checks before protected methods.

---

# Revision Notes

* AOP separates cross-cutting concerns.
* Aspect contains advice and pointcuts.
* `@Around` can measure duration and control execution.
* Spring AOP is proxy-based.
* Self-invocation can bypass aspects.
* Avoid logging sensitive information.

---

# Cheat Sheet

| Concept | Meaning |
| ------- | ------- |
| Aspect | Cross-cutting class |
| Advice | Code to execute |
| Pointcut | Where advice applies |
| Join point | Method execution point |
| `ProceedingJoinPoint` | Used by `@Around` |

---

# Interview Questions with Answers

### 1. What is AOP?

A programming approach for separating cross-cutting concerns from core business logic.

### 2. Why is Spring AOP proxy-based?

Spring wraps beans with proxies and applies advice when methods are called through the proxy.

### 3. What is self-invocation issue?

A method call within the same class may not pass through the proxy, so advice may not run.

---

# Hands-on Exercises

### Exercise 1

Write an aspect that logs service method duration.

**Answer**

```java
@Around("execution(* com.example.service..*(..))")
Object measure(ProceedingJoinPoint pjp) throws Throwable {
    long start = System.nanoTime();
    try {
        return pjp.proceed();
    } finally {
        System.out.println(System.nanoTime() - start);
    }
}
```

