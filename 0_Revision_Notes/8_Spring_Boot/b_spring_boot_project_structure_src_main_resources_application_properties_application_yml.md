# Revision Notes

* `src/main/java` contains application code.
* `src/main/resources` contains config and static resources.
* `src/test/java` contains tests.
* `application.properties` and `application.yml` hold configuration.
* Main class has `@SpringBootApplication`.
* Package structure should follow business modules where possible.

---

# Cheat Sheet

| Path | Purpose |
| ---- | ------- |
| `src/main/java` | Java source |
| `src/main/resources` | Config/resources |
| `src/test/java` | Tests |
| `application.yml` | YAML config |
| `application.properties` | Properties config |

---

# Interview Questions with Answers

### 1. Where is application config kept?

Usually in `src/main/resources/application.properties` or `application.yml`.

### 2. What is main class?

Class with `main()` and `@SpringBootApplication`.

### 3. Why package structure matters?

It keeps code maintainable and affects component scanning boundaries.

