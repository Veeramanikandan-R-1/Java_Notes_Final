# Revision Notes

* Polymorphism means many forms.
* Method overloading is compile-time polymorphism.
* Method overriding is runtime polymorphism.
* Runtime polymorphism depends on actual object type.
* Parent/interface references can hold child objects.
* Use interfaces when behavior has multiple implementations.
* Polymorphism helps remove long `if-else` chains.

---

# Cheat Sheet

| Concept | Example |
| ------- | ------- |
| Overloading | `add(int,int)`, `add(double,double)` |
| Overriding | Child redefines parent method |
| Upcasting | `Parent p = new Child()` |
| Dynamic dispatch | Runtime method selection |
| Contract | Interface/abstract class |

---

# Interview Questions with Answers

### 1. What is runtime polymorphism?

Runtime selection of overridden method based on actual object type.

### 2. What is compile-time polymorphism?

Method overloading resolved by compiler based on method arguments.

### 3. Why use `@Override`?

It helps the compiler catch incorrect overriding.

---

# Hands-on Exercises

### Exercise 1

Create `Shape` with `Circle` and `Rectangle`.

**Answer**

```java
interface Shape {
    double area();
}

class Circle implements Shape {
    private final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }
}
```

