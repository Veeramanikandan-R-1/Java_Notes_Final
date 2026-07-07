# Packages in Java

**Subtopics:** Package Structure, Import

> Goal: Understand packages not just as a language feature, but as an architectural tool for building maintainable, production-grade backend applications.

---

# 1. Fundamentals

## What is a Package?

A **package** is a namespace that groups related Java classes, interfaces, enums, annotations, and records.

Think of it as a **folder + namespace**.

Without packages:

```
User
Order
Product
Service
Controller
```

Everything exists together, causing name collisions.

With packages:

```
com.company.ecommerce.user.User
com.company.ecommerce.order.Order
com.company.ecommerce.product.Product
```

Each class has a unique identity.

---

## Why Packages Exist

Packages solve several problems.

### 1. Namespace Management

Different libraries can have classes with the same name.

Example:

```
java.util.Date
java.sql.Date
```

Without packages this would be impossible.

---

### 2. Organize Code

Instead of one huge directory:

```
500 Java files
```

You organize by feature or layer.

```
controller
service
repository
model
config
```

---

### 3. Access Control

Packages work together with Java access modifiers.

Example:

```
class EmployeeService
```

may be visible only inside its package.

---

### 4. Better Maintainability

Large applications may contain

* 5000+ classes
* hundreds of developers

Packages keep everything manageable.

---

## Package Declaration

A package declaration must be the **first non-comment line**.

```java
package com.company.employee.service;
```

Then comes imports.

```java
package com.company.employee.service;

import java.util.List;

public class EmployeeService {

}
```

---

## Fully Qualified Class Name (FQCN)

Every Java class has a full name.

Example:

```
java.util.List
```

where

```
java      -> top-level package
util      -> subpackage
List      -> class
```

Similarly,

```
com.company.user.service.UserService
```

---

# 2. Package Structure

A package maps directly to the folder structure.

Example:

```java
package com.company.payment.service;
```

Directory:

```
src
 └── com
      └── company
            └── payment
                   └── service
                         PaymentService.java
```

The compiler expects this structure.

---

## Naming Convention

Always lowercase.

Good

```
com.amazon.payment

org.springframework.beans

java.util
```

Bad

```
Com.Company

Payment

SERVICE
```

Reason:

Some operating systems are case-sensitive.

---

## Reverse Domain Naming

Java uses company domains reversed.

Suppose domain is

```
amazon.com
```

Package becomes

```
com.amazon
```

Google

```
com.google
```

Spring

```
org.springframework
```

This ensures uniqueness worldwide.

---

## Subpackages

Packages are hierarchical in naming only.

```
com.company
```

is **not** the parent package of

```
com.company.service
```

These are separate packages.

Example:

```
com.company

com.company.service

com.company.model
```

Access rules do **not** automatically flow.

---

# Common Package Structures

## Layer-Based Structure

Most common in Spring Boot.

```
controller
service
repository
entity
dto
config
exception
```

Example

```
com.company.employee

    controller
    service
    repository
    entity
```

Simple and easy for CRUD applications.

---

## Feature-Based Structure (Preferred for large systems)

```
employee
    controller
    service
    repository

order
    controller
    service
    repository

payment
    controller
    service
```

Benefits

* high cohesion
* feature isolation
* easier ownership
* better scalability

Many modern backend teams prefer this.

---

# Import Statement

After package declaration comes imports.

Example

```java
package com.company;

import java.util.List;

public class Test {

}
```

---

## Why Import Exists

Without import:

```java
java.util.List<String> list;
```

With import:

```java
import java.util.List;

List<String> list;
```

Cleaner and easier to read.

---

# Types of Import

## 1. Single Type Import

```java
import java.util.List;
```

Recommended.

---

## 2. Wildcard Import

```java
import java.util.*;
```

Imports all public types from that package.

Example:

```
List

Map

Set

HashMap

Collections
```

are available.

---

### Internal Working

Wildcard **does NOT load every class**.

It only tells the compiler:

> "If a class is referenced, search this package."

So there is **no runtime performance difference**.

The difference is readability.

---

## 3. Static Import

Imports static members.

Without

```java
Math.PI
```

With

```java
import static java.lang.Math.PI;

System.out.println(PI);
```

Useful for

* constants
* utility methods
* testing (JUnit assertions)

---

Example

```java
import static java.lang.Math.max;

System.out.println(max(10,20));
```

---

# Default Imports

Java automatically imports

```java
java.lang.*
```

So you never write

```java
import java.lang.String;
```

or

```java
import java.lang.System;
```

because they're already available.

---

# Import Resolution Order

Suppose compiler sees

```java
List
```

It resolves in this order:

1. Current package
2. Explicit imports
3. Wildcard imports
4. `java.lang`
5. Fully qualified name if used directly

---

# Name Conflict

Suppose

```java
java.util.Date
```

and

```java
java.sql.Date
```

Both imported.

```java
import java.util.Date;
import java.sql.Date;
```

Compilation error.

Solution:

```java
java.util.Date utilDate =
        new java.util.Date();

java.sql.Date sqlDate =
        new java.sql.Date(...);
```

Use the fully qualified name for one (or both).

---

# Internal Working (Must Know)

## Compilation

During compilation

```java
import java.util.List;
```

is resolved.

The generated bytecode stores the **fully qualified class reference**.

There is **no runtime lookup of imports**.

Imports are a **compile-time convenience only**.

---

## Class Loading

The JVM does **not** use import statements.

It loads

```
java.util.List
```

using the fully qualified name embedded in the bytecode.

So

```
import
```

has zero effect after compilation.

---

## Package and Access Control

Package-private members

```java
class EmployeeHelper {

}
```

are accessible only within the same package.

This is heavily used for internal implementation classes.

---

# Common Mistakes

## 1. Wildcard Imports Everywhere

Bad

```java
import java.util.*;
```

Harder to know dependencies and can create ambiguity.

Prefer:

```java
import java.util.List;
import java.util.Map;
```

---

## 2. Wrong Folder Structure

Wrong

```
package com.company.user;
```

stored inside

```
payment/
```

The package declaration and directory structure should match the source layout.

---

## 3. Uppercase Package Names

Wrong

```
Com.Company
```

Always lowercase.

---

## 4. Huge Root Package

Bad

```
com.company
    400 classes
```

Split by feature or responsibility.

---

## 5. Circular Package Dependency

Bad

```
service
    depends on repository

repository
    depends on service
```

Creates tight coupling and makes testing harder.

---

# Best Practices

### Use reverse domain naming

```
com.company.project
```

---

### Keep package names meaningful

Good

```
payment

security

authentication
```

Bad

```
misc

common2

temp
```

---

### Prefer feature-based packages for large applications

```
order

payment

inventory
```

instead of one global

```
service
repository
controller
```

---

### Use explicit imports

Preferred

```java
import java.util.List;
```

Avoid wildcard imports unless enforced by project conventions.

---

### Hide internal implementation

Expose only public APIs; keep helper classes package-private when they are not meant to be used outside the package.

---

# Code Examples

## Example 1

```
com.company.user
        User.java
```

```java
package com.company.user;

public class User {

}
```

---

## Example 2

```
com.company.service
```

```java
package com.company.service;

import com.company.user.User;

public class UserService {

    private User user;
}
```

---

## Example 3

Static Import

```java
import static java.lang.Math.sqrt;

public class Demo {

    public static void main(String[] args) {
        System.out.println(sqrt(25));
    }
}
```

---

## Example 4

Fully Qualified Name

```java
public class Demo {

    public static void main(String[] args) {

        java.util.List<String> list =
                new java.util.ArrayList<>();
    }
}
```

---

# Real-World Scenario

Consider an e-commerce application.

```
com.shop

    user
        controller
        service
        repository
        entity
        dto

    product
        controller
        service
        repository
        entity

    payment
        controller
        service
        repository
        entity

    common
        exception
        config
        util
```

Why this works well:

* Related classes stay together.
* Teams can own individual features (`payment`, `user`, etc.).
* Changes in one feature are less likely to impact others.
* Public APIs are easier to identify, while package-private classes can remain internal to a feature.

---

# Revision Notes

* Package = namespace + organization.
* Package declaration must be the first non-comment statement.
* Folder structure should match the package structure.
* Packages are not hierarchical for access control.
* Imports are compile-time only.
* JVM ignores import statements at runtime.
* `java.lang.*` is imported automatically.
* Prefer explicit imports over wildcard imports.
* Use reverse domain naming.
* Feature-based packaging scales better for large backend systems.
* Use fully qualified names when class names conflict.
* Package-private access is useful for internal implementation classes.

---

# Cheat Sheet

| Concept             | Key Point                                    |
| ------------------- | -------------------------------------------- |
| Package             | Groups related classes into a namespace      |
| FQCN                | `com.company.service.UserService`            |
| Package Declaration | First non-comment line                       |
| Import              | Compile-time convenience                     |
| JVM                 | Doesn't use imports                          |
| Default Import      | `java.lang.*`                                |
| Explicit Import     | Recommended                                  |
| Wildcard Import     | `*` imports available types, not subpackages |
| Static Import       | Imports static members                       |
| Package Naming      | Lowercase                                    |
| Large Projects      | Prefer feature-based packaging               |
| Name Conflict       | Use fully qualified names                    |

---

# Interview Questions with Answers

### 1. Why do we use packages in Java?

**Answer:** To organize related code, avoid class name conflicts, enforce access control boundaries, and improve maintainability in large applications.

---

### 2. Are packages hierarchical?

**Answer:** No. `com.company` and `com.company.service` are independent packages. Access modifiers do not inherit across them.

---

### 3. What is the difference between a package and a folder?

**Answer:** A package is a Java namespace declared in source code, while a folder is the filesystem representation. By convention and compiler expectations, the directory structure matches the package declaration.

---

### 4. Does wildcard import load every class?

**Answer:** No. It only allows the compiler to resolve referenced classes from that package. Classes are loaded by the JVM only when needed.

---

### 5. Does import affect runtime performance?

**Answer:** No. Imports are resolved during compilation. The bytecode contains fully qualified type references.

---

### 6. Why is `java.lang` imported automatically?

**Answer:** It contains fundamental classes (`Object`, `String`, `System`, `Math`, `Exception`, etc.) that almost every Java program uses, so the compiler implicitly imports it.

---

### 7. What happens if two imported classes have the same name?

**Answer:** The compiler reports an ambiguity. Use the fully qualified name for one (or both) classes.

---

### 8. When should you use static imports?

**Answer:** For well-known constants and utility methods (e.g., `Math.PI`, JUnit assertions). Avoid overusing them if they reduce readability.

---

# Hands-on Exercises

## Exercise 1 — Create Packages

**Question:** Create the following package structure:

```
com.company.employee
com.company.department
```

Create an `Employee` class in the first package and a `Department` class in the second.

**Answer:**

```java
package com.company.employee;

public class Employee {
}
```

```java
package com.company.department;

public class Department {
}
```

---

## Exercise 2 — Import a Class

**Question:** Use `Employee` inside `EmployeeService`.

**Answer:**

```java
package com.company.service;

import com.company.employee.Employee;

public class EmployeeService {

    private Employee employee;
}
```

---

## Exercise 3 — Resolve a Name Conflict

**Question:** Use both `java.util.Date` and `java.sql.Date` in the same class.

**Answer:**

```java
public class Demo {

    public static void main(String[] args) {

        java.util.Date utilDate = new java.util.Date();

        java.sql.Date sqlDate =
                new java.sql.Date(System.currentTimeMillis());
    }
}
```

---

## Exercise 4 — Static Import

**Question:** Use `sqrt()` without qualifying it with `Math`.

**Answer:**

```java
import static java.lang.Math.sqrt;

public class Demo {

    public static void main(String[] args) {
        System.out.println(sqrt(81));
    }
}
```

---

## Exercise 5 — Design a Package Structure

**Question:** Design packages for a Library Management System.

**Answer:**

```
com.library

    book
        controller
        service
        repository
        entity

    member
        controller
        service
        repository
        entity

    loan
        controller
        service
        repository
        entity

    common
        config
        exception
        util
```

This structure keeps each business capability self-contained and is a good foundation for a production-grade backend application.
