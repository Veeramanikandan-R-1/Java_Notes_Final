# Entity Mapping

### Subtopic: Entities

---

# 1. Fundamentals

An entity is a Java class mapped to a database table.

JPA defines the mapping standard. Hibernate is the most commonly used JPA implementation.

```java
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
```

---

# 2. Core Concepts

## `@Entity`

Marks a class as persistent. Hibernate tracks objects of this class inside the persistence context.

## `@Id`

Every entity needs a primary key.

```java
@Id
private Long id;
```

## Generated Identifiers

```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

Common strategies:

| Strategy | Meaning |
| -------- | ------- |
| `IDENTITY` | Database auto-increment |
| `SEQUENCE` | Database sequence |
| `AUTO` | Provider chooses |

## Column Mapping

```java
@Column(name = "email", nullable = false, unique = true, length = 120)
private String email;
```

Use annotations when database names or constraints need to be explicit.

## Constructors

JPA requires a no-args constructor with at least protected visibility.

```java
protected Customer() {
}
```

---

# 3. Internal Working

Hibernate creates metadata from entity annotations at startup. During runtime, it tracks managed entity objects and detects changes through dirty checking.

When a managed entity field changes inside a transaction, Hibernate can generate an `UPDATE` during flush without calling `save` again.

---

# 4. Common Mistakes

* Missing no-args constructor.
* Using entity classes directly as API request/response DTOs.
* Including mutable relationships blindly in `toString`, `equals`, or `hashCode`.
* Changing primary key values after persistence.
* Using primitive `long` for nullable generated IDs.
* Treating annotations as validation without database constraints.

---

# 5. Best Practices

* Keep entities focused on persistence and domain state.
* Use DTOs at API boundaries.
* Prefer `Long` or UUID for identifiers.
* Make constructors intentional.
* Keep `equals` and `hashCode` simple and safe.
* Match important Java constraints with database constraints.

---

# 6. Code Example

```java
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    protected Customer() {
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void changeEmail(String email) {
        this.email = email;
    }
}
```

---

# 7. Real-world Scenarios

* Mapping customers, orders, products, and payments.
* Persisting domain objects through repositories.
* Applying database constraints through entity metadata.
* Updating managed objects inside service transactions.

---

# Revision Notes

* Entity maps Java class to database table.
* `@Id` defines primary key.
* `@GeneratedValue` controls ID generation.
* JPA requires a no-args constructor.
* Hibernate tracks managed entities for dirty checking.
* Do not expose entities directly as API DTOs.

---

# Cheat Sheet

| Need | Annotation |
| ---- | ---------- |
| Entity class | `@Entity` |
| Table name | `@Table` |
| Primary key | `@Id` |
| Generated ID | `@GeneratedValue` |
| Column config | `@Column` |
| Ignore persistence | `@Transient` |

---

# Interview Questions with Answers

### 1. What is an entity?

A persistent Java object mapped to a database table.

### 2. Why does JPA need a no-args constructor?

The provider uses it to instantiate entities through reflection or generated proxies.

### 3. What is dirty checking?

Hibernate detects changes in managed entities and synchronizes them with the database during flush.

---

# Hands-on Exercises

### Exercise 1

Create a `Product` entity with id, name, and price.

**Answer**

```java
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    protected Product() {}
}
```

