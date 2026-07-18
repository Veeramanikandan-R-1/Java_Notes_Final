# Revision Notes

* Entity maps a Java class to a database table.
* JPA is the specification; Hibernate is an implementation.
* `@Entity` marks persistent classes.
* `@Id` defines the primary key.
* `@GeneratedValue` controls ID generation.
* JPA needs a no-args constructor.
* Hibernate dirty checks managed entities.
* Use DTOs for API input/output, not entities.

---

# Cheat Sheet

| Need | Annotation |
| ---- | ---------- |
| Entity | `@Entity` |
| Table | `@Table` |
| ID | `@Id` |
| Generated ID | `@GeneratedValue` |
| Column settings | `@Column` |
| Not persisted | `@Transient` |

---

# Interview Questions with Answers

### 1. What is an entity?

A Java object mapped to a database table and managed by JPA.

### 2. Why use wrapper `Long` for generated ID?

Because new entities have null IDs before persistence.

### 3. What is dirty checking?

Hibernate detects changes in managed entities and writes updates during flush.

---

# Hands-on Exercises

### Exercise 1

Create entity ID mapping.

**Answer**

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

