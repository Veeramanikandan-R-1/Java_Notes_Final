# Maven

### Subtopic: POM, Dependencies

---

# 1. Fundamentals

Maven is a Java build and dependency management tool.

It standardizes:

* Project structure
* Dependency management
* Build lifecycle
* Plugin execution
* Packaging

Main config file:

```text
pom.xml
```

---

# 2. Core Concepts

## Standard Project Structure

```text
src/main/java
src/main/resources
src/test/java
src/test/resources
pom.xml
```

Maven expects this structure by convention.

---

## POM

`pom.xml` means Project Object Model.

```xml
<project>
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>1.0.0</version>
</project>
```

Coordinates:

| Field | Meaning |
| ----- | ------- |
| `groupId` | Organization/package identity |
| `artifactId` | Project/module name |
| `version` | Artifact version |

---

## Dependencies

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

Maven downloads dependencies from repositories and adds them to classpath.

---

## Dependency Scope

| Scope | Meaning |
| ----- | ------- |
| `compile` | Default, needed for compile and runtime |
| `test` | Only for tests |
| `runtime` | Needed at runtime, not compile |
| `provided` | Provided by environment |

---

## Build Lifecycle

Common phases:

```text
validate -> compile -> test -> package -> verify -> install -> deploy
```

Useful commands:

```bash
mvn clean
mvn test
mvn package
mvn install
```

---

# 3. Internal Working

Maven resolves dependency tree transitively.

If your project depends on A, and A depends on B, Maven can bring B automatically.

Conflicts are resolved mostly by nearest dependency rule.

Use:

```bash
mvn dependency:tree
```

to debug dependency versions.

---

# 4. Common Mistakes

* Not checking dependency conflicts.
* Adding unnecessary dependencies.
* Using wrong dependency scope.
* Hardcoding versions everywhere.
* Not using dependency management in multi-module projects.
* Skipping tests blindly.

---

# 5. Best Practices

* Keep dependencies minimal.
* Use `dependency:tree` for conflicts.
* Use BOM/dependency management for consistent versions.
* Use wrapper script when available.
* Do not commit generated `target/`.
* Keep build reproducible.

---

# 6. Code Example

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>3.3.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

---

# 7. Real-world Scenarios

* Building Spring Boot apps.
* Managing third-party libraries.
* Running unit tests.
* Packaging JAR/WAR files.
* Multi-module backend projects.

---

# Revision Notes

* Maven is build and dependency management tool.
* `pom.xml` is Maven config.
* Coordinates are `groupId`, `artifactId`, `version`.
* Dependencies are transitive.
* Scopes control classpath usage.
* Lifecycle includes compile, test, package, install.
* Use `mvn dependency:tree` for conflicts.

---

# Cheat Sheet

| Need | Command/Tag |
| ---- | ----------- |
| Clean | `mvn clean` |
| Test | `mvn test` |
| Package | `mvn package` |
| Install local | `mvn install` |
| Dependency tree | `mvn dependency:tree` |
| Config file | `pom.xml` |

---

# Interview Questions with Answers

### 1. What is Maven?

A build automation and dependency management tool for Java projects.

### 2. What is POM?

Project Object Model file that defines project metadata, dependencies, plugins, and build configuration.

### 3. What is transitive dependency?

A dependency brought indirectly through another dependency.

---

# Hands-on Exercises

### Exercise 1

Add JUnit dependency with test scope.

**Answer**

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
```

