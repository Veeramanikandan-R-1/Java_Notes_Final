# Revision Notes

* Auto-configuration configures beans based on classpath and properties.
* `@SpringBootApplication` includes auto-configuration, component scan, and configuration.
* Boot backs off when user-defined bean exists.
* Starters influence auto-configuration.
* Conditions decide whether config applies.
* Use debug report to inspect auto-config decisions.

---

# Cheat Sheet

| Concept | Meaning |
| ------- | ------- |
| Auto-config | Automatic bean setup |
| Starter | Dependency bundle |
| Condition | Controls config activation |
| Back off | User bean wins |
| Main annotation | `@SpringBootApplication` |

---

# Interview Questions with Answers

### 1. What is auto-configuration?

Spring Boot automatically creates beans based on dependencies, properties, and existing beans.

### 2. How does Boot know what to configure?

Through auto-configuration classes and conditional annotations.

### 3. Can we override auto-config?

Yes, by defining our own beans or changing properties.

