## Upcasting & Downcasting in Java

These are **reference/object casting**, mainly used with **inheritance**.

Assume:

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barking");
    }
}
```

Here:

```text
Animal  ← Parent
  ↑
 Dog   ← Child
```

---

## 1. Upcasting

**Child object → Parent reference**

Java does this **automatically**.

```java
Dog dog = new Dog();

Animal animal = dog;  // ✅ Upcasting
```

Or simply:

```java
Animal animal = new Dog(); // ✅
```

Think:

```text
Dog object
    ↓
Animal reference
```

### What can you access?

Through the `Animal` reference, you can only access members known to `Animal`:

```java
animal.sound(); // ✅
animal.bark();  // ❌ Compile error
```

Even though the actual object is a `Dog`, the reference type is `Animal`.

### Why useful?

Very common for **polymorphism**:

```java
Animal animal = new Dog();
animal.sound();
```

If `Dog` overrides `sound()`, Java calls the `Dog` implementation at runtime.

---

## 2. Downcasting

**Parent reference → Child reference**

You must explicitly cast:

```java
Animal animal = new Dog();

Dog dog = (Dog) animal;  // ✅ Downcasting

dog.sound(); // ✅
dog.bark();  // ✅
```

Now you can access `Dog`-specific methods.

```text
Animal reference
      ↓
   (Dog)
      ↓
Dog reference
```

---

## ⚠️ Downcasting can fail

This is important.

```java
Animal animal = new Animal();

Dog dog = (Dog) animal; // ❌ Runtime ClassCastException
```

Why?

The actual object is an `Animal`, **not a Dog**.

```text
Reference type: Animal
Actual object:  Animal ❌
Trying to cast: Dog
```

### Safe downcasting

Use `instanceof`:

```java
Animal animal = new Dog();

if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
    dog.bark();
}
```

---

## Upcasting vs Downcasting

|            | Upcasting              | Downcasting                    |
| ---------- | ---------------------- | ------------------------------ |
| Direction  | Child → Parent         | Parent → Child                 |
| Automatic? | ✅ Yes                  | ❌ No                           |
| Syntax     | `Animal a = new Dog()` | `Dog d = (Dog) a`              |
| Risk       | Safe                   | Can cause `ClassCastException` |
| Main use   | Polymorphism           | Access child-specific behavior |

### ⭐ Remember

```java
Animal a = new Dog();       // UPCASTING ✅

Dog d = (Dog) a;            // DOWNCASTING ✅
```

**Simple rule:**

> **Upcasting is safe and automatic. Downcasting is explicit and must be done carefully.**
