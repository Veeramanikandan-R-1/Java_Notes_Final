# Singleton Design Pattern

### Subtopic: Singleton

---

# 1. Fundamentals

Singleton ensures a class has only one instance and provides global access to it.

Use it for shared, stateless, or expensive-to-create objects.

---

# 2. Core Concepts

## Eager Singleton

```java
public final class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }
}
```

Simple and thread-safe because instance is created during class loading.

---

## Lazy Singleton

```java
public final class LazyConfig {
    private static LazyConfig instance;

    private LazyConfig() {
    }

    public static synchronized LazyConfig getInstance() {
        if (instance == null) {
            instance = new LazyConfig();
        }
        return instance;
    }
}
```

Thread-safe but synchronization adds overhead.

---

## Enum Singleton

```java
public enum ConfigSingleton {
    INSTANCE;
}
```

Best protection against serialization and reflection issues in many cases.

---

# 3. Internal Working

Singleton depends on:

* Private constructor
* Static instance
* Public access method

Thread safety matters when instance is lazily created.

---

# 4. Common Mistakes

* Not making constructor private.
* Lazy singleton without synchronization.
* Making singleton hold mutable global state.
* Making testing difficult through hidden dependencies.
* Reimplementing singleton in Spring where singleton bean scope already exists.

---

# 5. Best Practices

* Prefer dependency injection in Spring applications.
* Keep singleton stateless where possible.
* Use enum singleton for plain Java when suitable.
* Avoid using singleton as global variable storage.
* Be careful with serialization and reflection.

---

# 6. Real-world Scenarios

* Configuration holder.
* Logger facade.
* Shared stateless utility.
* Spring singleton-scoped beans.

---

# Revision Notes

* Singleton creates one instance.
* Constructor must be private.
* Eager singleton is simple and thread-safe.
* Lazy singleton needs thread-safety.
* Enum singleton is robust.
* Avoid mutable global state.
* In Spring, singleton scope is default.

---

# Cheat Sheet

| Type | Use |
| ---- | --- |
| Eager | Simple always-needed instance |
| Lazy synchronized | Created on demand |
| Enum | Robust plain Java singleton |
| Spring bean | Preferred in Spring apps |

---

# Interview Questions with Answers

### 1. What problem does Singleton solve?

It ensures only one instance exists and provides controlled access to it.

### 2. Is Singleton always good?

No. It can hide dependencies and create global mutable state.

### 3. Why enum singleton?

It handles serialization and reflection better than many manual implementations.

