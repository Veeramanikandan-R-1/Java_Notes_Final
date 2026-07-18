# Gradle

### Subtopic: Build Scripts

---

# 1. Fundamentals

Gradle is a build automation tool used for Java, Kotlin, Android, and multi-language projects.

It uses build scripts instead of XML.

Common files:

```text
build.gradle
settings.gradle
gradle.properties
```

Modern projects often use Kotlin DSL:

```text
build.gradle.kts
settings.gradle.kts
```

---

# 2. Core Concepts

## Plugins

Plugins add build capabilities.

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.3.0'
}
```

---

## Repositories

Repositories tell Gradle where to download dependencies.

```groovy
repositories {
    mavenCentral()
}
```

---

## Dependencies

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation 'org.junit.jupiter:junit-jupiter'
}
```

Common configurations:

| Configuration | Meaning |
| ------------- | ------- |
| `implementation` | Compile/runtime dependency, not exposed |
| `api` | Exposed to consumers in library projects |
| `runtimeOnly` | Runtime only |
| `testImplementation` | Test compile/runtime |

---

## Tasks

Gradle work is task-based.

```bash
gradle tasks
gradle test
gradle build
```

Wrapper command:

```bash
./gradlew build
```

On Windows:

```powershell
.\gradlew.bat build
```

---

# 3. Internal Working

Gradle builds a task graph.

It determines which tasks depend on others and executes only what is needed.

Gradle supports incremental builds and build cache, which can make large projects faster.

---

# 4. Common Mistakes

* Not using Gradle wrapper.
* Mixing old and new dependency configurations.
* Putting too much logic in build scripts.
* Not understanding multi-project builds.
* Ignoring dependency conflicts.
* Using dynamic versions like `1.+` in production.

---

# 5. Best Practices

* Use Gradle wrapper.
* Keep build scripts simple.
* Prefer fixed dependency versions.
* Use version catalogs for large projects.
* Use `implementation` by default.
* Use `dependencies` and `dependencyInsight` for debugging.
* Keep custom tasks readable.

---

# 6. Code Example

```groovy
plugins {
    id 'java'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.17.0'
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
}

test {
    useJUnitPlatform()
}
```

---

# 7. Real-world Scenarios

* Spring Boot builds.
* Multi-module services.
* Android builds.
* Fast incremental local builds.
* CI build pipelines.

---

# Revision Notes

* Gradle is task-based build tool.
* Build scripts are usually Groovy or Kotlin DSL.
* Plugins add build behavior.
* Repositories define dependency sources.
* Dependencies use configurations like `implementation`.
* Gradle wrapper gives consistent build version.
* Gradle builds a task graph.

---

# Cheat Sheet

| Need | Command/File |
| ---- | ------------ |
| Build file | `build.gradle` |
| Settings | `settings.gradle` |
| Build | `gradle build` |
| Test | `gradle test` |
| Wrapper build | `./gradlew build` |
| List tasks | `gradle tasks` |
| Dependency debug | `gradle dependencyInsight` |

---

# Interview Questions with Answers

### 1. What is Gradle?

A flexible build automation tool based on tasks and build scripts.

### 2. Maven vs Gradle?

Maven is XML/lifecycle based. Gradle is script/task based and often more flexible.

### 3. Why use Gradle wrapper?

It ensures every developer and CI uses the same Gradle version.

---

# Hands-on Exercises

### Exercise 1

Add dependency in Gradle.

**Answer**

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

