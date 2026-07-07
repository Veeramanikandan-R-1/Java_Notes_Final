# Access Modifiers in Java

**Subtopics:** `public`, `private`, `protected`, `default (package-private)`

> **Goal:** Understand access modifiers as a mechanism for **encapsulation, API design, and maintainability**, not just visibility control. In production systems, choosing the right access modifier is a design decision.

---

# 1. Fundamentals

## What are Access Modifiers?

Access modifiers define **who can access a class, method, constructor, or field**.

They help Java enforce **encapsulation**, one of the four pillars of Object-Oriented Programming.

Example:

```java
public class Employee {

    private String name;

    public String getName() {
        return name;
    }
}
```

Here,

* `Employee` is accessible everywhere.
* `name` is accessible only inside `Employee`.
* `getName()` exposes controlled access.

---

## Why Do We Need Access Modifiers?

Without access control:

* Any class could modify internal data.
* Business rules could be bypassed.
* Code becomes tightly coupled.
* Refactoring becomes risky.

Example:

Bad

```java
public class BankAccount {

    public double balance;
}
```

Anyone can do:

```java
account.balance = -50000;
```

Business rules are broken.

Better

```java
public class BankAccount {

    private double balance;

    public void deposit(double amount) {
        balance += amount;
    }
}
```

Now validation can be enforced.

---

## Where Can Access Modifiers Be Used?

| Element         | Allowed Modifiers   |
| --------------- | ------------------- |
| Top-level class | `public`, `default` |
| Field           | All four            |
| Method          | All four            |
| Constructor     | All four            |
| Nested class    | All four            |

**Note:** Top-level classes cannot be `private` or `protected`.

---

# Understanding Visibility

Consider two packages:

```text
com.company.employee

    Employee
    EmployeeService

com.company.payment

    PaymentService
```

Now imagine another class:

```text
External Library
```

Access modifiers decide which classes can access members across these boundaries.

---

# 2. The Four Access Modifiers

---

# 1. public

## What?

Accessible from **everywhere**.

Example

```java
public class Employee {

    public void save() {

    }
}
```

Can be accessed from:

* Same class
* Same package
* Different package
* Different package subclass
* Any external project (if dependency is added)

---

## When to Use

Use `public` only for:

* Public APIs
* Controllers
* Service interfaces
* DTOs
* Utility methods meant for consumers

Think of `public` as:

> "This is part of my application's contract."

---

# 2. private

## What?

Accessible **only inside the same class**.

Example

```java
public class Employee {

    private String salary;
}
```

Only methods inside `Employee` can access it.

---

## Why Use private?

Encapsulation.

Instead of

```java
employee.salary = -100;
```

Use

```java
employee.setSalary(5000);
```

where validation happens.

---

Example

```java
public class Employee {

    private int age;

    public void setAge(int age) {

        if(age < 18)
            throw new IllegalArgumentException();

        this.age = age;
    }
}
```

Business rules remain protected.

---

## When to Use

Use `private` by default unless broader access is required.

This is called the **Principle of Least Privilege**.

---

# 3. default (Package-Private)

If no modifier is specified:

```java
class EmployeeHelper {

}
```

or

```java
void process() {

}
```

It is package-private.

Accessible only inside the same package.

---

Example

```text
com.company.employee

    Employee
    EmployeeHelper
```

Both can access each other.

But

```text
com.company.payment.PaymentService
```

cannot.

---

## Why Does It Exist?

Many helper classes should remain internal to a package.

Example

```text
payment

    PaymentService

    PaymentValidator

    PaymentCalculator

    PaymentMapper
```

Only `PaymentService` may need to be public, while helper classes remain package-private.

This prevents accidental usage by other packages.

---

## When to Use

* Internal implementation classes
* Mappers
* Validators
* Package utilities
* Internal algorithms

Very common in production code.

---

# 4. protected

Protected combines:

* package-private access
* subclass access across packages

---

Example

```java
public class Animal {

    protected void eat() {

    }
}
```

Subclass

```java
public class Dog extends Animal {

    void test() {
        eat();
    }
}
```

Works even if `Dog` is in another package.

---

But

```java
Animal animal = new Animal();

animal.eat();
```

outside the package is not allowed.

Protected is primarily designed for inheritance.

---

## When to Use

Use only when subclasses are expected to extend behavior.

Examples:

Frameworks

```text
Spring
Hibernate
JUnit
```

often expose extension points using `protected`.

---

# Visibility Matrix

| Modifier  | Same Class | Same Package | Subclass (Different Package) | Other Package |
| --------- | ---------- | ------------ | ---------------------------- | ------------- |
| public    | ✅          | ✅            | ✅                            | ✅             |
| protected | ✅          | ✅            | ✅                            | ❌             |
| default   | ✅          | ✅            | ❌                            | ❌             |
| private   | ✅          | ❌            | ❌                            | ❌             |

This table is frequently asked in interviews.

---

# Internal Working (Must Know)

## During Compilation

The compiler checks whether access is legal.

Example

```java
Employee employee = new Employee();

employee.salary;
```

If `salary` is private:

Compilation fails.

The JVM never executes it.

---

## JVM Enforcement

Access modifiers are stored in the class file as **access flags** (for example, `ACC_PUBLIC`, `ACC_PRIVATE`, `ACC_PROTECTED`).

During method and field resolution, the JVM verifies these flags before allowing access. Illegal access results in runtime access checks (or compilation errors in normal Java code).

---

## Reflection

Reflection can bypass access modifiers.

Example

```java
field.setAccessible(true);
```

This is mainly used by:

* Spring
* Hibernate
* Jackson

Normal application code should avoid relying on this because it weakens encapsulation.

---

# Common Mistakes

## 1. Making Everything public

Bad

```java
public int age;

public String name;

public double salary;
```

Everything becomes mutable.

---

## 2. Using protected Without Inheritance

Bad

```java
protected void helper() {

}
```

when no subclass will ever use it.

Use package-private or private instead.

---

## 3. Exposing Internal Helper Classes

Bad

```java
public class PaymentValidator {

}
```

If it's only used within the `payment` package, keep it package-private.

---

## 4. Forgetting Encapsulation

Bad

```java
public List<String> items;
```

Anyone can modify it.

Better

```java
private final List<String> items = new ArrayList<>();

public List<String> getItems() {
    return List.copyOf(items);
}
```

---

## 5. Using Getters and Setters Blindly

Avoid exposing every field just because IDEs can generate accessors. Expose only what your domain requires.

---

# Best Practices

## 1. Start with private

Default mindset:

```java
private
```

Increase visibility only when necessary.

---

## 2. Minimize Public API

Every public method becomes part of your contract.

Changing it later may break consumers.

---

## 3. Prefer Package-Private for Internal Collaboration

Example

```text
payment

    PaymentService (public)

    PaymentValidator (default)

    PaymentCalculator (default)
```

Keeps implementation hidden.

---

## 4. Use protected Only for Extension

Good use case

```java
protected void beforeSave() {

}
```

Subclasses override it.

Avoid using `protected` as a substitute for package-private.

---

## 5. Keep Fields private

Even constants should generally be:

```java
private static final
```

unless they are part of a public API.

---

# Code Examples

## Example 1 — private

```java
public class Employee {

    private String name;

    public String getName() {
        return name;
    }
}
```

---

## Example 2 — public

```java
public class EmployeeService {

    public void save() {

    }
}
```

---

## Example 3 — default

```java
class EmployeeValidator {

    boolean validate() {
        return true;
    }
}
```

Accessible only within the same package.

---

## Example 4 — protected

```java
public class Animal {

    protected void sound() {
        System.out.println("Animal");
    }
}

public class Dog extends Animal {

    @Override
    protected void sound() {
        System.out.println("Dog");
    }
}
```

---

## Example 5 — Top-Level Class

Valid

```java
public class Employee {

}
```

Valid

```java
class EmployeeHelper {

}
```

Invalid

```java
private class Employee {

}
```

Top-level classes cannot be `private` or `protected`.

---

# Real-World Scenario

Consider a payment module:

```text
com.company.payment

    PaymentController
    PaymentService
    PaymentValidator
    PaymentCalculator
    PaymentRepository
```

Design:

```text
PaymentController      public
PaymentService         public
PaymentRepository      public (Spring Data interface)
PaymentValidator       default
PaymentCalculator      default
```

Why?

* Other modules need `PaymentService`.
* Internal validation and calculation logic should not be directly accessible.
* The service acts as the package's public entry point, reducing coupling and making future refactoring easier.

---

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
