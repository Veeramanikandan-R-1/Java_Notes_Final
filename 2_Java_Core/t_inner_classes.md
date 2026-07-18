# Inner Classes in Java

### Subtopic: Nested, Local, Anonymous

---

# 1. Fundamentals

An inner class is a class declared inside another class or block.

```java
class Outer {
    class Inner {
    }
}
```

Use inner classes when a helper type is strongly related to the enclosing class.

---

# 2. Core Concepts

## 2.1 Non-static Inner Class

It belongs to an instance of outer class.

```java
class Order {
    private String id = "ORD-1";

    class Audit {
        void print() {
            System.out.println(id);
        }
    }
}
```

It can access outer instance members.

## 2.2 Static Nested Class

Static nested class does not need outer object.

```java
class ApiResponse {
    static class Metadata {
        int page;
        int size;
    }
}
```

Use it for helper types grouped under an outer class.

## 2.3 Local Class

A local class is declared inside a method.

```java
void process() {
    class Validator {
        boolean valid(String value) {
            return value != null && !value.isBlank();
        }
    }
}
```

Use rarely, when the class is needed only inside one method.

## 2.4 Anonymous Class

Anonymous class has no name.

```java
Runnable task = new Runnable() {
    public void run() {
        System.out.println("Running");
    }
};
```

For functional interfaces, lambda is usually cleaner.

```java
Runnable task = () -> System.out.println("Running");
```

---

# 3. Internal Working

Non-static inner classes hold a reference to the outer instance. This allows access to outer members but can also accidentally keep the outer object alive.

Static nested classes do not hold that hidden outer reference.

---

# 4. Common Mistakes

* Using non-static inner class when static nested class is enough.
* Creating complex anonymous classes instead of named classes.
* Using anonymous classes where lambda is clearer.
* Accidentally causing memory leaks by keeping inner class references.
* Making code harder to test by hiding too much inside methods.

---

# 5. Best Practices

* Prefer static nested class if outer instance is not needed.
* Use anonymous classes for one-off implementations with state or multiple methods.
* Use lambdas for functional interfaces.
* Keep inner classes small.
* Move reusable inner classes to top-level classes.

---

# 6. Code Example

```java
class Pagination {
    private final int page;
    private final int size;

    Pagination(int page, int size) {
        this.page = page;
        this.size = size;
    }

    static class Sort {
        private final String field;
        private final String direction;

        Sort(String field, String direction) {
            this.field = field;
            this.direction = direction;
        }
    }
}
```

---

# 7. Real-world Scenarios

* DTO metadata classes.
* Builder helper classes.
* Event handlers.
* Comparators before lambdas.
* Small strongly-related helper types.

---

# Revision Notes

* Inner class is declared inside another class.
* Non-static inner class needs outer instance.
* Static nested class does not need outer instance.
* Local class is declared inside a method.
* Anonymous class has no name.
* Lambdas often replace anonymous classes for functional interfaces.

---

# Cheat Sheet

| Type | Use |
| ---- | --- |
| Inner class | Needs outer instance |
| Static nested | Group helper type |
| Local class | Method-only helper |
| Anonymous class | One-off implementation |
| Lambda | Functional interface |

---

# Interview Questions with Answers

### 1. Difference between inner and static nested class?

Inner class needs an outer instance. Static nested class does not.

### 2. What is anonymous class?

A one-off unnamed class usually used to implement an interface or extend a class.

### 3. Why prefer static nested class?

It avoids hidden outer object reference when outer state is not needed.

---

# Hands-on Exercises

### Exercise 1

Create static nested `Builder` class.

**Answer**

```java
class User {
    private String name;

    static class Builder {
        private final User user = new User();

        Builder name(String name) {
            user.name = name;
            return this;
        }

        User build() {
            return user;
        }
    }
}
```

