# OOP in Java

### Subtopic: Classes and Objects

---

# 1. Fundamentals

A class is a blueprint. An object is a runtime instance of that blueprint.

```java
class User {
    String name;
}

User user = new User();
user.name = "Ravi";
```

The class defines structure and behavior. The object holds actual state.

---

# 2. Core Concepts

## Class

A class groups fields and methods.

```java
class Account {
    String accountNumber;
    double balance;

    void deposit(double amount) {
        balance += amount;
    }
}
```

## Object

An object is created using `new`.

```java
Account account = new Account();
```

`account` is a reference variable pointing to an object on the heap.

## Fields

Fields store object state.

```java
private String email;
```

## Methods

Methods define object behavior.

```java
public boolean isActive() {
    return active;
}
```

## Constructor

A constructor initializes the object.

```java
class User {
    private final String email;

    User(String email) {
        this.email = email;
    }
}
```

## this Keyword

`this` refers to the current object.

```java
this.email = email;
```

---

# 3. Internal Working

When `new User()` runs:

1. JVM allocates memory on heap.
2. Fields get default values.
3. Constructor runs.
4. Reference is returned.

```java
User user = new User("a@test.com");
```

`user` is not the object itself. It is a reference to the object.

---

# 4. Common Mistakes

* Creating classes with public mutable fields.
* Putting all logic in `main()` instead of modeling behavior.
* Confusing reference comparison with object equality.
* Forgetting constructors should create valid objects.
* Creating anemic classes with only getters and setters when behavior belongs inside the class.

---

# 5. Best Practices

* Keep fields private.
* Initialize required state through constructors.
* Put behavior near the data it uses.
* Keep classes focused on one responsibility.
* Use meaningful class and method names.
* Avoid exposing internal mutable state.

---

# 6. Code Example

```java
class BankAccount {
    private final String accountNumber;
    private double balance;

    BankAccount(String accountNumber, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.balance = openingBalance;
    }

    void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
    }

    double getBalance() {
        return balance;
    }
}
```

---

# 7. Real-world Scenarios

* `User`, `Order`, and `Invoice` model business concepts.
* `PaymentService` models service behavior.
* `Repository` classes model database access.
* DTO classes model API input and output.

---

# Revision Notes

* Class is a blueprint.
* Object is an instance.
* Fields store state.
* Methods define behavior.
* Constructor initializes object.
* `this` refers to current object.
* Objects live on heap.
* Reference variables point to objects.

---

# Cheat Sheet

| Concept | Meaning |
| ------- | ------- |
| Class | Blueprint |
| Object | Runtime instance |
| Field | State |
| Method | Behavior |
| Constructor | Initialization |
| `this` | Current object |

---

# Interview Questions with Answers

### 1. What is a class?

A blueprint that defines fields and methods.

### 2. What is an object?

A runtime instance of a class.

### 3. Why use constructors?

To initialize objects into a valid state.

### 4. Where are objects stored?

Objects are stored on the heap. References may be stored in stack frames or object fields.

---

# Hands-on Exercises

### Exercise 1

Create a `Book` class with title and author.

**Answer**

```java
class Book {
    private final String title;
    private final String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
```

