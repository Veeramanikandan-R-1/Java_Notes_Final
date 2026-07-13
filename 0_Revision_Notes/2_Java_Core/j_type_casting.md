# Revision Notes

* Type casting converts one type into another.
* Implicit casting is automatic and safe.
* Explicit casting is manual and may lose data.
* Integer division removes decimals.
* `byte`, `short`, and `char` are promoted to `int` during arithmetic.
* Downcasting requires explicit casting.
* Invalid downcasting causes `ClassCastException`.
* Use `instanceof` before downcasting.

---

# Cheat Sheet

| Conversion            | Automatic | Manual |
| --------------------- | --------- | ------ |
| byte → int            | ✅         | ❌      |
| int → long            | ✅         | ❌      |
| long → double         | ✅         | ❌      |
| double → int          | ❌         | ✅      |
| int → byte            | ❌         | ✅      |
| Superclass → Subclass | ❌         | ✅      |
| Subclass → Superclass | ✅         | ❌      |

**Remember:**

```
Small → Large = Automatic (Widening)

Large → Small = Manual (Narrowing)
```

Arithmetic promotion:

```
byte + byte → int
short + short → int
char + char → int
```

---

# Interview Questions with Answers

### 1. What is type casting in Java?

Converting a value from one data type to another, either automatically (implicit) or manually (explicit).

---

### 2. What is widening casting?

Automatic conversion from a smaller data type to a larger compatible data type without data loss.

Example:

```java
int x = 10;
long y = x;
```

---

### 3. What is narrowing casting?

Manual conversion from a larger data type to a smaller one, which may lose data or overflow.

Example:

```java
double d = 5.9;
int i = (int) d;
```

---

### 4. Why does `byte + byte` return `int`?

Java promotes `byte`, `short`, and `char` to `int` before performing arithmetic to simplify operations and align with CPU instruction efficiency.

---

### 5. What is the difference between upcasting and downcasting?

* **Upcasting:** Subclass → Superclass, automatic and safe.
* **Downcasting:** Superclass → Subclass, explicit and requires runtime type compatibility.

---

# Hands-on Exercises

### Exercise 1

Convert an `int` to `double` using implicit casting.

**Answer**

```java
int n = 10;
double d = n;
```

---

### Exercise 2

Convert a `double` to `int` and observe the output.

**Answer**

```java
double d = 45.89;
int n = (int) d;

System.out.println(n);
```

Output:

```
45
```

---

### Exercise 3

Write a program that calculates the average of two integers and returns the correct decimal value.

**Answer**

```java
int a = 5;
int b = 2;

double avg = (double) (a + b) / 2;

System.out.println(avg);
```

Output:

```
3.5
```

---

### Exercise 4

Demonstrate overflow by casting an `int` to `byte`.

**Answer**

```java
int value = 130;

byte b = (byte) value;

System.out.println(b);
```

Output:

```
-126
```

---

### Exercise 5

Create an `Animal` superclass and a `Dog` subclass. Perform safe downcasting using `instanceof`.

**Answer**

```java
class Animal {}

class Dog extends Animal {
    void bark() {
        System.out.println("Woof!");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();

        if (animal instanceof Dog) {
            Dog dog = (Dog) animal;
            dog.bark();
        }
    }
}
```

Output:

```
Woof!
```