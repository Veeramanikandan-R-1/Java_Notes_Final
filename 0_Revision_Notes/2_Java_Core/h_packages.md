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