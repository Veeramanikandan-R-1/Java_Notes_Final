# Config Server - Centralized Config

Act as a Principal Java Backend Engineer.

Topic To Teach: Config Server  
Subtopic: Centralized Config

## 1. Fundamentals

Spring Cloud Config Server centralizes application configuration for multiple services. Instead of packaging every config value inside each service, services fetch environment-specific configuration at startup.

It is useful when many services need consistent, versioned configuration across environments.

## 2. Core Concepts

### Config Server

The server exposes configuration from a backend such as Git, file system, Vault, or another source.

### Config Client

Each service fetches its configuration using its application name and active profile.

### Profiles

Profiles separate environments such as `dev`, `qa`, `stage`, and `prod`.

### Externalized Configuration

Values such as URLs, feature flags, timeouts, and thresholds should be externalized. Secrets require stronger handling, usually Vault, cloud secret managers, or encrypted values.

## 3. Server Example

```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

```yaml
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/company/platform-config.git
```

Client:

```yaml
spring:
  application:
    name: order-service
  config:
    import: optional:configserver:http://localhost:8888
  profiles:
    active: dev
```

## 4. Internal Working

When `order-service` starts with profile `dev`, it requests configuration such as:

```text
/order-service/dev
```

The config server resolves matching files, merges property sources, and returns them to the client. Spring adds them to the environment so `@ConfigurationProperties` and `@Value` can use them.

## 5. Common Mistakes

- Storing plain secrets in Git.
- Making service startup fully dependent on an unavailable config server without a strategy.
- Changing config without audit or review.
- Overusing dynamic refresh for values that should require deployment.
- Putting business data in configuration.
- Not validating required config on startup.

## 6. Best Practices

- Use Git-backed config for auditable non-secret values.
- Use secret managers for passwords, tokens, and keys.
- Bind config with `@ConfigurationProperties`.
- Validate config using Jakarta Bean Validation.
- Keep environment-specific differences small.
- Version and review production config changes.
- Define a clear refresh strategy.

## 7. Code Example

```java
@ConfigurationProperties(prefix = "payment")
@Validated
public record PaymentProperties(
        @NotBlank String baseUrl,
        @Min(100) int connectTimeoutMs,
        @Min(100) int readTimeoutMs
) {}
```

Enable properties:

```java
@ConfigurationPropertiesScan
@SpringBootApplication
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
```

## 8. Real-World Scenario

In production, `order-service` needs different payment timeout values from staging. The values live in a reviewed config repository. Secrets such as payment provider keys come from Vault, not plain YAML.

## Revision Notes

- Config Server centralizes external configuration.
- Clients fetch config by app name and profile.
- Git is good for auditable non-secret config.
- Secrets need secure storage.
- Validate config at startup.

## Cheat Sheet

| Item | Recommendation |
|---|---|
| URLs | Config Server |
| Timeouts | Config Server |
| Feature flags | Config Server or flag platform |
| Passwords | Secret manager |
| Business records | Database |

## Interview Q&A

**Q: Why use centralized config?**  
A: To manage environment-specific values consistently and audit changes without rebuilding services.

**Q: Should secrets be stored in Git config?**  
A: No. Use Vault, cloud secret managers, or encrypted secret workflows.

**Q: Why prefer `@ConfigurationProperties`?**  
A: It gives structured, testable, validated configuration instead of scattered string keys.

## Hands-on Exercises

1. Create a config server backed by a local Git repo.
2. Add `order-service-dev.yml`.
3. Bind timeout values using `@ConfigurationProperties`.
