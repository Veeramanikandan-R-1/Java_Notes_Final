# OOP in Java

### Subtopic: Inheritance

---

# 1. Fundamentals

Inheritance allows one class to acquire fields and methods from another class.

```java
class Animal {
    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking");
    }
}
```

`Dog` is a subclass. `Animal` is a superclass.

---

# 2. Core Concepts

## extends

Java uses `extends` for class inheritance.

```java
class SavingsAccount extends Account {
}
```

Java supports single inheritance for classes. A class can extend only one class.

## Method Overriding

A subclass can provide its own implementation.

```java
class EmailNotification {
    void send() {
        System.out.println("Sending email");
    }
}

class SmsNotification extends EmailNotification {
    @Override
    void send() {
        System.out.println("Sending SMS");
    }
}
```

## super Keyword

`super` refers to the parent class.

```java
class Child extends Parent {
    Child() {
        super();
    }
}
```

Use it to call parent constructors or methods.

## Constructor Flow

Parent constructor runs before child constructor.

```java
class Parent {
    Parent() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    Child() {
        System.out.println("Child");
    }
}
```

Output:

```text
Parent
Child
```

---

# 3. Internal Working

A subclass object contains superclass state as part of itself.

```java
Dog dog = new Dog();
```

The object has both `Animal` behavior and `Dog` behavior.

At runtime, overridden methods are resolved dynamically.

```java
Animal animal = new Dog();
animal.eat();
```

If `eat()` is overridden in `Dog`, the `Dog` version runs.

---

# 4. Common Mistakes

* Using inheritance only for code reuse.
* Creating deep inheritance chains.
* Breaking parent class contracts in child classes.
* Overriding methods without `@Override`.
* Confusing "is-a" with "has-a".

Bad inheritance:

```java
class Invoice extends ArrayList<String> {
}
```

Invoice is not a list. It has line items.

---

# 5. Best Practices

* Use inheritance for true "is-a" relationships.
* Prefer composition for "has-a" relationships.
* Keep inheritance hierarchies shallow.
* Use `@Override`.
* Design parent classes carefully.
* Use interfaces for flexible contracts.

---

# 6. Code Example

```java
abstract class Payment {
    private final double amount;

    Payment(double amount) {
        this.amount = amount;
    }

    double amount() {
        return amount;
    }

    abstract void process();
}

class CardPayment extends Payment {
    CardPayment(double amount) {
        super(amount);
    }

    @Override
    void process() {
        System.out.println("Processing card payment: " + amount());
    }
}
```

---

# 7. Real-world Scenarios

* Base exception classes.
* Abstract payment types.
* Common audit fields in mapped superclass designs.
* Framework base classes.
* Specialized service behavior.

---

# Revision Notes

* Inheritance models an "is-a" relationship.
* Java class inheritance uses `extends`.
* Java supports single class inheritance.
* Parent constructor runs before child constructor.
* `super` accesses parent constructor or behavior.
* Overridden methods are resolved at runtime.
* Prefer composition for "has-a" relationships.

---

# Cheat Sheet

| Concept | Meaning |
| ------- | ------- |
| `extends` | Inherit class |
| Superclass | Parent class |
| Subclass | Child class |
| `super` | Parent reference |
| `@Override` | Marks overridden method |
| Composition | Use object as field |

---

# Interview Questions with Answers

### 1. What is inheritance?

A mechanism where a child class reuses and specializes parent class behavior.

### 2. Does Java support multiple class inheritance?

No. A class can extend only one class.

### 3. Why prefer composition sometimes?

Composition avoids tight coupling and deep inheritance problems.

---

# Hands-on Exercises

### Exercise 1

Create `Vehicle` and `Car` using inheritance.

**Answer**

```java
class Vehicle {
    void start() {
        System.out.println("Starting");
    }
}

class Car extends Vehicle {
    void drive() {
        System.out.println("Driving");
    }
}
```

