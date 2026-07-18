# Revision Notes

* `@Configuration` marks class as bean definition source.
* `@Bean` registers method return value as Spring bean.
* `@ConfigurationProperties` binds external config to typed class.
* Use typed config instead of scattered `@Value`.
* Keep configuration classes focused.
* Validate important properties.

---

# Cheat Sheet

| Need | Annotation |
| ---- | ---------- |
| Config class | `@Configuration` |
| Register bean | `@Bean` |
| Bind properties | `@ConfigurationProperties` |
| Simple value | `@Value` |
| Enable properties | `@EnableConfigurationProperties` |

---

# Interview Questions with Answers

### 1. @Bean vs @Component?

`@Component` marks class for scanning. `@Bean` registers object returned by a method.

### 2. Why use @ConfigurationProperties?

It provides type-safe grouped configuration.

### 3. Where keep environment config?

In external configuration like properties/yml, environment variables, or config server.

