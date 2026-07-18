# Spring Core

### Subtopic: Bean Lifecycle - Spring Context

---

# 1. Fundamentals

The bean lifecycle is the journey of a Spring-managed object from definition to destruction.

Spring does not only create objects. It also injects dependencies, applies post-processors, calls initialization methods, and shuts beans down gracefully.

---

# 2. Core Concepts

## ApplicationContext

`ApplicationContext` is the central Spring container.

It stores bean definitions, creates beans, publishes events, resolves messages, and manages lifecycle callbacks.

---

## Lifecycle Flow

```text
Bean definition loaded
-> Bean instantiated
-> Dependencies injected
-> Aware callbacks
-> BeanPostProcessor before initialization
-> Initialization callback
-> BeanPostProcessor after initialization
-> Bean ready
-> Destruction callback
```

---

## Initialization Options

```java
@PostConstruct
void init() {
    System.out.println("Bean initialized");
}
```

For `@Bean` methods:

```java
@Bean(initMethod = "start", destroyMethod = "stop")
CacheClient cacheClient() {
    return new CacheClient();
}
```

---

## Destruction Options

```java
@PreDestroy
void close() {
    System.out.println("Bean destroyed");
}
```

Destruction callbacks are mostly relevant for singleton beans that hold resources.

---

# 3. Internal Working

Spring first builds bean definitions from annotations, XML, or Java configuration.

For singleton beans, Spring usually creates them during context startup.

`BeanPostProcessor` can wrap beans with proxies. This is how features like AOP, transactions, and async behavior are commonly applied.

---

# 4. Common Mistakes

* Calling business methods from constructors before dependencies are ready.
* Doing heavy network calls during startup without timeout handling.
* Forgetting to close resource-heavy beans.
* Assuming prototype beans always receive destroy callbacks.
* Not realizing AOP proxies may wrap the original bean.

---

# 5. Best Practices

* Keep constructors simple.
* Use `@PostConstruct` for lightweight initialization.
* Use `@PreDestroy` for releasing resources.
* Prefer explicit timeout and retry policies for startup checks.
* Avoid placing business workflow inside lifecycle callbacks.

---

# 6. Code Example

```java
@Component
class ReportCache {
    @PostConstruct
    void warmUp() {
        System.out.println("Warm cache");
    }

    @PreDestroy
    void shutdown() {
        System.out.println("Close cache");
    }
}
```

---

# 7. Real-world Scenarios

* Opening and closing connection pools.
* Starting message consumers.
* Preparing local caches.
* Registering application metrics.
* Releasing file handles during shutdown.

---

# Revision Notes

* `ApplicationContext` manages bean lifecycle.
* Dependencies are injected after object creation.
* `@PostConstruct` runs after dependency injection.
* `@PreDestroy` runs before singleton bean destruction.
* `BeanPostProcessor` can modify or proxy beans.
* Keep lifecycle methods lightweight and reliable.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Init method | `@PostConstruct` |
| Destroy method | `@PreDestroy` |
| Java config init | `@Bean(initMethod = "...")` |
| Java config destroy | `@Bean(destroyMethod = "...")` |
| Container | `ApplicationContext` |

---

# Interview Questions with Answers

### 1. What is bean lifecycle?

The process Spring follows to create, configure, initialize, use, and destroy a bean.

### 2. When does `@PostConstruct` run?

After dependency injection and before the bean is used.

### 3. Why are proxies important in lifecycle?

Spring may wrap beans with proxies to provide AOP, transactions, async, or security behavior.

---

# Hands-on Exercises

### Exercise 1

Create a bean that logs when it is initialized and destroyed.

**Answer**

```java
@Component
class StartupLogger {
    @PostConstruct
    void init() {
        System.out.println("Started");
    }

    @PreDestroy
    void destroy() {
        System.out.println("Stopped");
    }
}
```

