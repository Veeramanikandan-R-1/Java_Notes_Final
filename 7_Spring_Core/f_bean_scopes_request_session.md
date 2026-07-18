# Spring Core

### Subtopic: Bean Scopes - Request, Session

---

# 1. Fundamentals

Request and session scopes are web-aware bean scopes.

They are available in Spring web applications where an HTTP request and user session exist.

---

# 2. Core Concepts

## Request Scope

A request-scoped bean lives for one HTTP request.

```java
@Component
@RequestScope
class RequestMetadata {
    private String correlationId;
}
```

Each HTTP request gets a separate instance.

---

## Session Scope

A session-scoped bean lives for one HTTP session.

```java
@Component
@SessionScope
class ShoppingCart {
    private final List<String> itemIds = new ArrayList<>();
}
```

The same user session receives the same instance across requests.

---

## Scoped Proxy

When a singleton service injects a request-scoped bean, Spring commonly uses a proxy.

The proxy is singleton-safe, but it resolves the real request bean at runtime.

---

# 3. Internal Working

Spring stores request-scoped beans in request attributes and session-scoped beans in session attributes.

If a scoped bean is injected into a singleton, Spring injects a proxy because the real object does not exist during application startup.

At runtime, the proxy delegates to the actual bean associated with the current request or session.

---

# 4. Common Mistakes

* Storing sensitive data in session scope without clear cleanup.
* Using session scope in stateless REST APIs without a strong reason.
* Injecting request data into singleton fields manually.
* Using request scope in async threads without context propagation.
* Making request-scoped beans too large.

---

# 5. Best Practices

* Prefer stateless services for REST APIs.
* Use request scope for correlation IDs, tenant context, or request metadata.
* Use session scope carefully for web UI flows.
* Avoid large session objects.
* Treat scoped proxies as runtime lookups, not real singleton state.

---

# 6. Code Example

```java
@Component
@RequestScope
class TenantContext {
    private String tenantId;

    String getTenantId() {
        return tenantId;
    }

    void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}

@Service
class InvoiceService {
    private final TenantContext tenantContext;

    InvoiceService(TenantContext tenantContext) {
        this.tenantContext = tenantContext;
    }
}
```

---

# 7. Real-world Scenarios

* Correlation ID per request.
* Tenant information in multi-tenant APIs.
* Shopping cart in server-rendered web apps.
* User wizard state during multi-step forms.

---

# Revision Notes

* Request scope creates one bean per HTTP request.
* Session scope creates one bean per HTTP session.
* Web scopes require a web application context.
* Scoped proxies allow scoped beans inside singleton beans.
* Stateless REST APIs should avoid unnecessary session state.
* Request context may not automatically exist in async processing.

---

# Cheat Sheet

| Scope | Lifetime |
| ----- | -------- |
| `@RequestScope` | One HTTP request |
| `@SessionScope` | One HTTP session |
| Singleton | One application context |
| Prototype | New per container lookup |

---

# Interview Questions with Answers

### 1. What is request scope?

A scope where Spring creates one bean instance for each HTTP request.

### 2. What is session scope?

A scope where one bean instance is stored for an HTTP session.

### 3. Why are scoped proxies needed?

They let singleton beans reference scoped beans whose real instances are created later at request time.

---

# Hands-on Exercises

### Exercise 1

Create a request-scoped bean to store a correlation ID.

**Answer**

```java
@Component
@RequestScope
class CorrelationContext {
    private String correlationId;
}
```

