# Revision Notes

* Spring Context manages bean lifecycle.
* Bean lifecycle includes creation, dependency injection, initialization, usage, and destruction.
* `@PostConstruct` runs after dependencies are injected.
* `@PreDestroy` runs before bean destruction.
* `ApplicationContext` is the central Spring container.
* Lifecycle hooks are useful for resource setup and cleanup.

---

# Cheat Sheet

| Need | Tool |
| ---- | ---- |
| Container | `ApplicationContext` |
| After creation | `@PostConstruct` |
| Before destroy | `@PreDestroy` |
| Define bean | `@Component`, `@Bean` |
| Startup logic | `ApplicationRunner` |

---

# Interview Questions with Answers

### 1. What is ApplicationContext?

Spring container that manages beans, configuration, lifecycle, and dependency injection.

### 2. What is bean lifecycle?

The phases from bean creation to initialization, usage, and destruction.

### 3. When use @PostConstruct?

For initialization logic after dependency injection is complete.

