# Revision Notes

* `@OneToMany` maps one parent to many children.
* The many side usually owns the foreign key.
* Parent collection uses `mappedBy`.
* Child uses `@ManyToOne` and `@JoinColumn`.
* Helper methods keep both sides synchronized.
* Keep collections lazy.
* Use orphan removal only for true child lifecycle.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Parent collection | `@OneToMany(mappedBy = "...")` |
| Child owner | `@ManyToOne` |
| FK column | `@JoinColumn` |
| Persist child | Cascade persist/all |
| Delete orphan | `orphanRemoval = true` |

---

# Interview Questions with Answers

### 1. Why does the many side own the relation?

Because the foreign key is usually in the child table.

### 2. Why initialize collection fields?

To avoid null checks and support safe helper methods.

### 3. Why avoid eager collections?

They can load large graphs and cause expensive SQL.

---

# Hands-on Exercises

### Exercise 1

Write parent side for order items.

**Answer**

```java
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
private List<OrderItem> items = new ArrayList<>();
```

