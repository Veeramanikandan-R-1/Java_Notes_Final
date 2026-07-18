# Comparable and Comparator in Java

### Subtopic: Sorting

---

# 1. Fundamentals

Java uses `Comparable` and `Comparator` to sort custom objects.

`Comparable` defines natural ordering inside the class.

`Comparator` defines external/custom ordering.

---

# 2. Core Concepts

## Comparable

Use when a class has one natural sort order.

```java
class Employee implements Comparable<Employee> {
    private final int id;

    Employee(int id) {
        this.id = id;
    }

    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }
}
```

Sort:

```java
Collections.sort(employees);
```

## Comparator

Use when sorting logic is external or multiple sort orders are needed.

```java
Comparator<Employee> byName =
        Comparator.comparing(Employee::getName);
```

Reverse:

```java
Comparator<Employee> bySalaryDesc =
        Comparator.comparing(Employee::getSalary).reversed();
```

Multiple fields:

```java
Comparator<Employee> byDeptThenName =
        Comparator.comparing(Employee::getDepartment)
                .thenComparing(Employee::getName);
```

## Sorting Lists

```java
employees.sort(byDeptThenName);
```

## Null Handling

```java
Comparator<String> comparator =
        Comparator.nullsLast(String::compareTo);
```

---

# 3. Internal Working

Sorting algorithms repeatedly compare two elements.

`compare(a, b)` or `a.compareTo(b)` should return:

* negative if first is smaller
* zero if equal for sorting
* positive if first is greater

The comparison must be consistent and transitive.

---

# 4. Common Mistakes

* Returning only `1` or `-1` and never `0`.
* Subtracting integers directly and causing overflow.
* Making comparison inconsistent.
* Confusing natural order with custom order.
* Not handling null when null values are possible.

Bad:

```java
return this.id - other.id;
```

Good:

```java
return Integer.compare(this.id, other.id);
```

---

# 5. Best Practices

* Use `Comparable` for natural order.
* Use `Comparator` for flexible external sorting.
* Use `Integer.compare`, `Long.compare`, and comparator helpers.
* Use `thenComparing()` for multi-field sort.
* Handle nulls explicitly.
* Keep compare logic consistent with equality when required by sorted sets/maps.

---

# 6. Code Example

```java
class Product {
    private final String name;
    private final double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    String getName() {
        return name;
    }

    double getPrice() {
        return price;
    }
}

Comparator<Product> byPriceThenName =
        Comparator.comparingDouble(Product::getPrice)
                .thenComparing(Product::getName);
```

---

# 7. Real-world Scenarios

* Sort users by name.
* Sort invoices by date.
* Sort products by price.
* Sort reports by priority.
* Use sorted collections like `TreeSet` and `TreeMap`.

---

# Revision Notes

* `Comparable` defines natural ordering.
* `Comparator` defines external custom ordering.
* `compareTo()` belongs to the class.
* `compare()` belongs to comparator.
* Use comparator helpers.
* Avoid direct subtraction.
* Handle null values explicitly.

---

# Cheat Sheet

| Need | Code |
| ---- | ---- |
| Natural order | `implements Comparable<T>` |
| Custom order | `Comparator.comparing(...)` |
| Reverse | `.reversed()` |
| Multi-field | `.thenComparing(...)` |
| Primitive double | `comparingDouble(...)` |
| Null last | `Comparator.nullsLast(...)` |

---

# Interview Questions with Answers

### 1. Comparable vs Comparator?

Comparable is natural ordering inside the class. Comparator is external custom ordering.

### 2. Why avoid subtraction in compare?

It can overflow and produce incorrect ordering.

### 3. What does compare return?

Negative, zero, or positive depending on ordering.

---

# Hands-on Exercises

### Exercise 1

Sort employees by salary descending.

**Answer**

```java
employees.sort(Comparator.comparing(Employee::getSalary).reversed());
```

