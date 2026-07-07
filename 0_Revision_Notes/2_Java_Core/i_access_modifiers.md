# Revision Notes

* Access modifiers control visibility and enforce encapsulation.
* `private` → same class only.
* `default` (package-private) → same package only.
* `protected` → same package + subclasses in other packages.
* `public` → accessible everywhere.
* Top-level classes support only `public` or package-private.
* The compiler performs access checks during compilation; the JVM also enforces access using class-file access flags.
* Use `private` by default.
* Keep your public API as small as possible.
* Use package-private for internal package collaboration.
* Use `protected` only when inheritance is part of the design.

---

# Cheat Sheet

| Modifier    | Same Class | Same Package | Subclass (Other Package) | Other Package | Typical Use                             |
| ----------- | ---------- | ------------ | ------------------------ | ------------- | --------------------------------------- |
| `private`   | ✅          | ❌            | ❌                        | ❌             | Fields, internal methods                |
| `default`   | ✅          | ✅            | ❌                        | ❌             | Package helpers, validators, mappers    |
| `protected` | ✅          | ✅            | ✅                        | ❌             | Framework extension points, inheritance |
| `public`    | ✅          | ✅            | ✅                        | ✅             | Public APIs, services, controllers      |

---

# Interview Questions with Answers

### 1. What is the difference between `private` and `default`?

**Answer:** `private` restricts access to the same class only. `default` (package-private) allows access from any class within the same package.

---

### 2. What is the difference between `protected` and `default`?

**Answer:** Both allow access within the same package. `protected` additionally allows access from subclasses in different packages.

---

### 3. Can a top-level class be `private`?

**Answer:** No. A top-level class can only be `public` or package-private.

---

### 4. Which access modifier should you use by default?

**Answer:** `private`. Increase visibility only when there is a genuine requirement.

---

### 5. Why shouldn't everything be `public`?

**Answer:** It exposes implementation details, increases coupling, makes validation difficult, and limits future refactoring without breaking consumers.

---

### 6. Why is package-private useful?

**Answer:** It allows classes within the same package to collaborate while hiding implementation details from the rest of the application.

---

### 7. When should `protected` be used?

**Answer:** When a class is intentionally designed to be extended and subclasses need controlled access to internal behavior.

---

### 8. Can reflection access private members?

**Answer:** Yes, using APIs such as `setAccessible(true)` (subject to Java module system and security constraints). Frameworks like Spring and Hibernate use reflection, but application code should rely on normal access modifiers whenever possible.

---

# Hands-on Exercises

## Exercise 1 — Encapsulation

**Question:** Create an `Employee` class with a private `salary` field. Allow salary updates only if the value is positive.

**Answer:**

```java
public class Employee {

    private double salary;

    public void setSalary(double salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary must be positive");
        }
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}
```

---

## Exercise 2 — Package-Private Helper

**Question:** Create a package-private `EmailValidator` that is used only by `UserService`.

**Answer:**

```java
class EmailValidator {

    boolean isValid(String email) {
        return email.contains("@");
    }
}
```

```java
public class UserService {

    private final EmailValidator validator = new EmailValidator();

    public boolean register(String email) {
        return validator.isValid(email);
    }
}
```

---

## Exercise 3 — Inheritance with `protected`

**Question:** Allow subclasses to customize logging.

**Answer:**

```java
public class BaseService {

    protected void log(String message) {
        System.out.println(message);
    }
}

public class OrderService extends BaseService {

    public void createOrder() {
        log("Order created");
    }
}
```

---

## Exercise 4 — Fix the Design

**Question:** Improve this class:

```java
public class Product {

    public String name;
    public double price;
}
```

**Answer:**

```java
public class Product {

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }
}
```

---

## Exercise 5 — Choose the Correct Access Modifier

**Question:** Select the most appropriate modifier for each member.

| Member                                    | Recommended Modifier | Reason                               |
| ----------------------------------------- | -------------------- | ------------------------------------ |
| Domain object fields                      | `private`            | Preserve encapsulation               |
| Spring REST Controller                    | `public`             | Framework and clients must access it |
| Internal package validator                | `default`            | Used only within the package         |
| Hook method for subclasses                | `protected`          | Intended for inheritance             |
| Utility method used only inside one class | `private`            | No external access needed            |
