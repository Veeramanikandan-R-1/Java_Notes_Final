# Revision Notes

* GitHub Actions workflows live in `.github/workflows`.
* Jobs run on hosted or self-hosted runners.
* Steps use shell commands or reusable actions.
* Pull request workflows validate code before merge.
* `mvn -B clean verify` is a strong Maven CI command.
* Use dependency caching to speed up builds.
* Restrict permissions and protect secrets.
* Upload artifacts or reports when they help debugging.

---

# Cheat Sheet

| Need | YAML/Command |
| ---- | ------------ |
| Trigger PR | `on: pull_request` |
| Checkout | `actions/checkout@v4` |
| Setup JDK | `actions/setup-java@v4` |
| Maven cache | `cache: maven` |
| Build | `mvn -B clean verify` |
| Upload artifact | `actions/upload-artifact@v4` |

---

# Interview Questions with Answers

### 1. What is CI?

Continuous Integration validates changes frequently using automated builds and tests.

### 2. Why should CI run on pull requests?

To catch build, test, and quality problems before code reaches the main branch.

### 3. What is a runner?

The machine or environment where a workflow job executes.

---

# Hands-on Exercises

### Exercise 1

Set up Java 21 in a workflow.

**Answer**

```yaml
- uses: actions/setup-java@v4
  with:
    distribution: temurin
    java-version: "21"
```

