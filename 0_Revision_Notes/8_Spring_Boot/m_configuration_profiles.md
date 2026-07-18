# Revision Notes

* Profiles separate environment-specific configuration.
* Common profiles: dev, test, prod.
* Activate with `spring.profiles.active`.
* Profile-specific files: `application-dev.yml`.
* Avoid hardcoding environment values.
* Secrets should come from secure external source.

---

# Cheat Sheet

| Need | Config |
| ---- | ------ |
| Active profile | `spring.profiles.active=dev` |
| Dev config | `application-dev.yml` |
| Prod config | `application-prod.yml` |
| Conditional bean | `@Profile` |

---

# Interview Questions with Answers

### 1. What are profiles?

Profiles let Spring load different configuration for different environments.

### 2. Where keep prod secrets?

In secret manager/environment variables, not committed config files.

### 3. What is @Profile?

Annotation to load bean only for specific profiles.

