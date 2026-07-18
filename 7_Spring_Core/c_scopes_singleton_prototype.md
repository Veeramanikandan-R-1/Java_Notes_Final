# Spring Core

### Subtopic: Scopes - Singleton, Prototype

---

# 1. Fundamentals

Bean scope controls how many instances Spring creates and how long they live.

The two basic Spring scopes are singleton and prototype.

---

# 2. Core Concepts

## Singleton Scope

Singleton is the default Spring bean scope.

Spring creates one bean instance per application context.

```java
@Service
class PricingService {
}
```

Every dependency that asks for `PricingService` receives the same instance.

---

## Prototype Scope

Prototype creates a new bean instance every time it is requested from the container.

```java
@Component
@Scope("prototype")
class CsvParser {
}
```

Prototype is useful when the bean has temporary state.

---

## Singleton vs Prototype

| Scope | Instance Count | Common Use |
| ----- | -------------- | ---------- |
| Singleton | One per Spring context | Stateless services |
| Prototype | New per request from container | Stateful helper objects |

---

# 3. Internal Working

Singleton beans are created and cached by Spring.

Prototype beans are created on demand and returned to the caller. After creation, Spring does not manage the full destruction lifecycle of prototype beans in the same way it manages singleton beans.

Important case:

If a singleton directly injects a prototype, the prototype is resolved once when the singleton is created.

To request a fresh prototype from a singleton, use `ObjectProvider`.

---

# 4. Common Mistakes

* Storing request-specific data in singleton services.
* Assuming prototype inside singleton gives a new object for every method call.
* Making services prototype without a real reason.
* Using mutable fields in singleton beans without thread safety.
* Expecting Spring to automatically destroy prototype beans.

---

# 5. Best Practices

* Keep singleton services stateless.
* Use method-local variables for request data.
* Use prototype only for real stateful objects.
* Use `ObjectProvider<T>` when a singleton needs fresh instances.
* Avoid mutable shared fields unless they are thread-safe.

---

# 6. Code Example

```java
@Component
@Scope("prototype")
class ReportBuilder {
    private final List<String> rows = new ArrayList<>();
}

@Service
class ReportService {
    private final ObjectProvider<ReportBuilder> builders;

    ReportService(ObjectProvider<ReportBuilder> builders) {
        this.builders = builders;
    }

    ReportBuilder newBuilder() {
        return builders.getObject();
    }
}
```

---

# 7. Real-world Scenarios

* Singleton service for order processing.
* Prototype builder for report generation.
* Stateless repository bean shared across requests.
* Avoiding shared mutable state in web applications.

---

# Revision Notes

* Singleton is the default scope.
* Singleton means one bean per Spring context.
* Prototype means a new instance per container request.
* Prototype destruction is not fully managed like singleton destruction.
* Singleton beans should usually be stateless.
* Use `ObjectProvider` for fresh prototype instances inside singleton beans.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Default scope | Singleton |
| New instance per lookup | `@Scope("prototype")` |
| Fresh prototype from singleton | `ObjectProvider<T>` |
| Service state style | Stateless |

---

# Interview Questions with Answers

### 1. What is default Spring bean scope?

Singleton.

### 2. Is Spring singleton same as Java singleton pattern?

No. Spring singleton means one instance per Spring application context.

### 3. What happens when prototype is injected into singleton?

The prototype is usually created once during singleton creation unless lazy lookup is used.

---

# Hands-on Exercises

### Exercise 1

Create a prototype `OtpGenerator` and request a new instance from a singleton service.

**Answer**

```java
@Component
@Scope("prototype")
class OtpGenerator {
}

@Service
class OtpService {
    private final ObjectProvider<OtpGenerator> provider;

    OtpService(ObjectProvider<OtpGenerator> provider) {
        this.provider = provider;
    }

    OtpGenerator generator() {
        return provider.getObject();
    }
}
```

