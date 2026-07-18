# Revision Notes

* Maven is Java build and dependency management tool.
* `pom.xml` defines project configuration.
* Maven coordinates: `groupId`, `artifactId`, `version`.
* Maven uses standard project structure.
* Dependencies can be transitive.
* Scope controls where dependency is available.
* Common lifecycle phases: compile, test, package, install, deploy.
* Use `mvn dependency:tree` to debug conflicts.

---

# Cheat Sheet

| Need | Command/Concept |
| ---- | --------------- |
| Clean | `mvn clean` |
| Test | `mvn test` |
| Package | `mvn package` |
| Install | `mvn install` |
| Dependency tree | `mvn dependency:tree` |
| Config | `pom.xml` |
| Test dependency | `<scope>test</scope>` |

---

# Interview Questions with Answers

### 1. What is Maven?

A tool for building Java projects and managing dependencies.

### 2. What is dependency scope?

It controls where a dependency is available, such as compile, runtime, or test.

### 3. What is transitive dependency?

A dependency that comes indirectly through another dependency.

---

# Hands-on Exercises

### Exercise 1

Run tests using Maven.

**Answer**

```bash
mvn test
```

