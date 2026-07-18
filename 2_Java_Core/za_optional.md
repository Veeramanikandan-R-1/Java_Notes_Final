# Optional in Java

### Subtopic: Null Handling

---

# 1. Fundamentals

`Optional<T>` represents a value that may or may not be present.

```java
Optional<String> name = Optional.of("Java");
Optional<String> empty = Optional.empty();
```

It helps make absence explicit instead of returning null.

---

# 2. Core Concepts

## Creating Optional

```java
Optional<String> a = Optional.of("value");
Optional<String> b = Optional.ofNullable(possibleNull);
Optional<String> c = Optional.empty();
```

`of()` does not accept null.

## Checking Value

```java
if (name.isPresent()) {
    System.out.println(name.get());
}
```

Prefer functional APIs over `isPresent()` plus `get()`.

## orElse and orElseGet

```java
String result = name.orElse("default");
String result2 = name.orElseGet(() -> loadDefault());
```

`orElse()` evaluates argument immediately.

`orElseGet()` evaluates supplier only when empty.

## orElseThrow

```java
User user = repository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));
```

## map()

Transforms present value.

```java
Optional<String> email = userOptional.map(User::getEmail);
```

## flatMap()

Use when mapper already returns Optional.

```java
Optional<String> city = userOptional.flatMap(User::addressCity);
```

---

# 3. Internal Working

Optional is a wrapper object. It does not remove null from the JVM; it makes possible absence visible in method signatures.

Good use:

```java
Optional<User> findById(long id);
```

Bad use:

```java
class User {
    private Optional<String> email;
}
```

Avoid using Optional as entity field or DTO field.

---

# 4. Common Mistakes

* Calling `get()` without checking presence.
* Using Optional fields in entities.
* Using Optional for method parameters.
* Returning null from a method that returns Optional.
* Overusing Optional in internal code.
* Using `orElse()` when default value is expensive.

---

# 5. Best Practices

* Use Optional mainly as return type.
* Use `orElseThrow()` for required data.
* Use `map()` and `flatMap()` for transformations.
* Use `orElseGet()` for expensive defaults.
* Do not return null Optional.
* Do not use Optional for collections; return empty collection instead.

---

# 6. Code Example

```java
class UserService {
    private final UserRepository repository;

    UserService(UserRepository repository) {
        this.repository = repository;
    }

    String emailOf(long id) {
        return repository.findById(id)
                .map(User::getEmail)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
```

---

# 7. Real-world Scenarios

* Repository lookup by ID.
* Optional configuration value.
* First matching stream result.
* Avoiding null return from service helper methods.

---

# Revision Notes

* Optional represents possible absence.
* Use `ofNullable()` for nullable values.
* Avoid `get()` without presence check.
* Use `orElseThrow()` for required values.
* Use `orElseGet()` for expensive default.
* Use Optional mainly as return type.
* Do not use Optional fields in entities.
* Return empty collection instead of Optional collection.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Present | `Optional.of(value)` |
| Nullable | `Optional.ofNullable(value)` |
| Empty | `Optional.empty()` |
| Transform | `map()` |
| Nested optional | `flatMap()` |
| Default | `orElse()` |
| Lazy default | `orElseGet()` |
| Throw | `orElseThrow()` |

---

# Interview Questions with Answers

### 1. What is Optional?

A container that represents a value that may be present or absent.

### 2. orElse vs orElseGet?

`orElse()` evaluates default immediately. `orElseGet()` evaluates lazily only when needed.

### 3. Should Optional be used as field?

Generally no. Use it mainly as return type.

---

# Hands-on Exercises

### Exercise 1

Return username or throw if user is missing.

**Answer**

```java
String username = userRepository.findById(id)
        .map(User::getUsername)
        .orElseThrow(() -> new UserNotFoundException(id));
```

