# Revision Notes

* `@ManyToMany` maps many rows to many rows.
* It uses a join table.
* Owning side defines `@JoinTable`.
* Inverse side uses `mappedBy`.
* Prefer `Set` to avoid duplicate links.
* Avoid `CascadeType.REMOVE`.
* Use a join entity when association has fields.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Direct many-to-many | `@ManyToMany` |
| Join table | `@JoinTable` |
| Other side | `mappedBy` |
| Extra association data | Join entity |
| Collection | `Set` |
| Avoid | Remove cascade |

---

# Interview Questions with Answers

### 1. What does join table contain?

Foreign key pairs linking the two entity tables.

### 2. When use a join entity?

When the relationship has fields like status, date, quantity, grade, or audit data.

### 3. Why avoid remove cascade?

The related entity may be shared with other records.

---

# Hands-on Exercises

### Exercise 1

Map user roles.

**Answer**

```java
@ManyToMany
@JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
private Set<Role> roles = new HashSet<>();
```

