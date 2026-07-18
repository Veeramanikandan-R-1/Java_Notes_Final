# Revision Notes

* `@OneToOne` maps one row to one related row.
* Owning side has the foreign key.
* Owning side uses `@JoinColumn`.
* Inverse side uses `mappedBy`.
* Database uniqueness should enforce one-to-one.
* `@MapsId` uses shared primary key.
* Prefer lazy loading and verify SQL.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Own FK | `@JoinColumn` |
| Inverse side | `mappedBy` |
| Shared PK | `@MapsId` |
| Dependent child | `orphanRemoval = true` |
| Performance | `FetchType.LAZY` |

---

# Interview Questions with Answers

### 1. Who owns a one-to-one?

The side with the foreign key.

### 2. Why add unique constraint?

To ensure one parent links to only one child.

### 3. When use `@MapsId`?

When child identity depends on the parent's primary key.

---

# Hands-on Exercises

### Exercise 1

Map profile as one-to-one child of user.

**Answer**

```java
@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", unique = true)
private User user;
```

