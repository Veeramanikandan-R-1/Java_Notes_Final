# Revision Notes

* Method = reusable unit of behavior.
* Signature = name + parameter types.
* Java is always pass-by-value.
* Instance methods belong to objects.
* Static methods belong to classes.
* Overloading = compile-time polymorphism.
* Overriding = runtime polymorphism.
* JVM creates stack frames per method call.
* Objects live in heap, references in stack.
* Generic methods provide type safety.
* Small focused methods are easier to maintain.

---

# Cheat Sheet

```java
// Instance Method
void save()

// Static Method
static void save()

// Overloaded Methods
add(int a, int b)
add(double a, double b)

// Overridden Method
@Override
void process()

// Varargs
void print(String... values)

// Generic Method
<T> T get(T value)

// Final Method
final void validate()

// Method Reference
String::length

// Optional Return
Optional<User> findById(Long id)
```

---

# Interview Questions & Answers

### 1. What is a method signature?

Method name + parameter types.

---

### 2. Is return type part of method signature?

No.

---

### 3. Difference between overloading and overriding?

| Overloading          | Overriding               |
| -------------------- | ------------------------ |
| Compile time         | Runtime                  |
| Same method name     | Same signature           |
| Different parameters | Different implementation |

---

### 4. Is Java pass-by-reference?

No. Java is always pass-by-value.

---

### 5. Can static methods be overridden?

No. They are hidden, not overridden.

---

### 6. Why use `@Override`?

Compile-time safety and readability.

---

### 7. What causes `StackOverflowError`?

Excessive recursive method calls.

---

### 8. Why are small methods preferred?

Better readability, testing, and maintenance.

---

### 9. What is dynamic method dispatch?

Runtime selection of overridden methods based on actual object type.

---

### 10. Why are methods important in Spring?

Spring applies transactions, AOP, security, and proxies around method execution.

---

# Hands-On Exercises

## Exercise 1: Overloading

Create:

```java
add(int,int)
add(double,double)
add(int,int,int)
```

### Solution

```java
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

---

## Exercise 2: Overriding

Create `Vehicle`, `Car`, `Bike`.

Override:

```java
start()
```

### Solution

```java
class Vehicle {
    void start() {
        System.out.println("Vehicle Start");
    }
}

class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car Start");
    }
}
```

---

## Exercise 3: Generic Method

### Solution

```java
public class Util {

    public static <T> T identity(T value) {
        return value;
    }
}
```

Usage:

```java
String s = Util.identity("Hello");
Integer i = Util.identity(10);
```

---

## Exercise 4: Pass-by-Value

Predict output:

```java
class User {
    String name;
}

static void update(User user) {
    user.name = "John";
}
```

Output:

```text
John
```

Reason:

Copy of reference is passed, both references point to same object.

---

## Exercise 5: Refactoring

Refactor:

```java
processOrder()
```

into:

```java
validateOrder()
createOrder()
saveOrder()
publishOrderEvent()
```

Goal:

* Single Responsibility Principle
* Better testing
* Cleaner service layer

---

### Senior Backend Takeaway

For senior-level interviews, methods are not judged by syntax. They are judged by **API design, responsibility boundaries, side effects, extensibility, and maintainability**. A well-designed method should be **small, cohesive, predictable, and business-focused**.