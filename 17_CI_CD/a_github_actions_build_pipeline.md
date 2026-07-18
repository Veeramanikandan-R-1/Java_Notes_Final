# GitHub Actions

### Subtopic: Build Pipeline

---

# 1. Fundamentals

GitHub Actions automates build, test, scan, package, and release workflows from a repository.

For Java backend services, a build pipeline should answer one question quickly and reliably: is this change safe enough to merge or package?

Common pipeline stages:

```text
checkout -> setup JDK -> restore cache -> compile -> test -> package -> scan -> publish artifact
```

---

# 2. Core Concepts

## Workflow File

Workflows live under:

```text
.github/workflows/
```

Example trigger:

```yaml
on:
  pull_request:
  push:
    branches: [ main ]
```

Pull requests usually run validation. Pushes to `main` may also publish artifacts or images.

---

## Jobs and Steps

```yaml
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: "21"
      - run: mvn -B clean verify
```

A job runs on a runner. Steps run commands or reusable actions.

---

## Artifacts and Caching

Artifacts preserve build outputs. Caches speed up dependency downloads.

For Maven:

```yaml
with:
  cache: maven
```

Caching should improve speed without hiding dependency or test problems.

---

# 3. Internal Working

Each workflow run starts in a clean runner environment. The pipeline must install or declare everything it needs.

Secrets are injected at runtime and should never be printed. Pull requests from forks may not receive trusted secrets, which is an important security boundary.

The CI result becomes a merge signal when branch protection requires checks to pass.

---

# 4. Common Mistakes

* Running only compile and skipping tests.
* Using unpinned or untrusted third-party actions.
* Printing secrets in logs.
* Making one huge job that is slow and hard to diagnose.
* Not archiving test reports.
* Building a Docker image differently in CI than locally.
* Allowing flaky tests to become normal.

---

# 5. Best Practices

* Run CI on every pull request.
* Use `mvn -B clean verify` for non-interactive reliable builds.
* Enable dependency caching.
* Upload test reports and coverage where useful.
* Split independent checks when it improves feedback.
* Use branch protection for required checks.
* Build immutable artifacts from the commit being tested.
* Keep secrets scoped and use least privilege permissions.

---

# 6. Code Example

```yaml
name: Java Build

on:
  pull_request:
  push:
    branches: [ main ]

permissions:
  contents: read

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout
        uses: actions/checkout@v4

      - name: Set up Java
        uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: "21"
          cache: maven

      - name: Verify
        run: mvn -B clean verify

      - name: Upload JAR
        uses: actions/upload-artifact@v4
        with:
          name: app-jar
          path: target/*.jar
```

---

# 7. Real-world Scenarios

* Blocking merges when unit tests fail.
* Packaging a Spring Boot JAR for deployment.
* Running Testcontainers integration tests.
* Publishing Docker images after merge to `main`.
* Enforcing quality gates before production deployment.

---

# Revision Notes

* Workflows are YAML files in `.github/workflows`.
* Jobs run on runners.
* Steps execute commands or actions.
* Pull request CI protects merge quality.
* Caching speeds dependency resolution.
* Secrets must not be exposed in logs.

---

# Cheat Sheet

| Need | YAML/Command |
| ---- | ------------ |
| Checkout | `actions/checkout@v4` |
| Java setup | `actions/setup-java@v4` |
| Maven build | `mvn -B clean verify` |
| Maven cache | `cache: maven` |
| Artifact upload | `actions/upload-artifact@v4` |

---

# Interview Questions with Answers

### 1. What should a build pipeline do?

It should validate, test, package, and report whether a change is safe to merge or release.

### 2. Why use branch protection?

It prevents merging code unless required checks pass.

### 3. Why are fork pull requests special?

They are untrusted code, so secrets are restricted to reduce supply-chain risk.

---

# Hands-on Exercises

### Exercise 1

Write the Maven command usually used in CI.

**Answer**

```bash
mvn -B clean verify
```

