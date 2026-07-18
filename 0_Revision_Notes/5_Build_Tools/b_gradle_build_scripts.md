# Revision Notes

* Gradle is a task-based build automation tool.
* Build scripts use Groovy DSL or Kotlin DSL.
* `build.gradle` defines plugins, repositories, dependencies, and tasks.
* `settings.gradle` defines project settings and modules.
* Plugins add capabilities.
* Repositories define where dependencies are downloaded from.
* Use Gradle wrapper for consistent builds.
* Gradle builds a task graph and supports incremental builds.

---

# Cheat Sheet

| Need | Command/File |
| ---- | ------------ |
| Build script | `build.gradle` |
| Settings | `settings.gradle` |
| Build | `gradle build` |
| Test | `gradle test` |
| Wrapper | `./gradlew build` |
| Windows wrapper | `.\gradlew.bat build` |
| List tasks | `gradle tasks` |

---

# Interview Questions with Answers

### 1. What is Gradle?

A flexible build automation tool that uses tasks and build scripts.

### 2. Why use wrapper?

To guarantee the same Gradle version across machines and CI.

### 3. What is implementation dependency?

A dependency needed by the current module but not exposed as part of its public API.

---

# Hands-on Exercises

### Exercise 1

Run Gradle build using wrapper.

**Answer**

```bash
./gradlew build
```

