# Revision Notes

* Cascade propagates operations from parent to child.
* Use cascade only when child lifecycle belongs to parent.
* `PERSIST` saves child with parent.
* `MERGE` merges child with parent.
* `REMOVE` removes child when parent is removed.
* `ALL` includes all cascade operations.
* `orphanRemoval` deletes children removed from parent collection.
* Avoid remove cascade for shared relationships.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Save child | `CascadeType.PERSIST` |
| Merge graph | `CascadeType.MERGE` |
| Delete with parent | `CascadeType.REMOVE` |
| All cascade | `CascadeType.ALL` |
| Delete removed child | `orphanRemoval = true` |

---

# Interview Questions with Answers

### 1. Cascade remove vs orphan removal?

Cascade remove acts when parent is deleted. Orphan removal acts when child is removed from the relationship.

### 2. Should all relationships use cascade all?

No. Cascade only when lifecycle ownership is clear.

### 3. Why avoid remove cascade in many-to-many?

Because the target can be shared by other parents.

---

# Hands-on Exercises

### Exercise 1

Configure cart items as owned children.

**Answer**

```java
@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
private List<CartItem> items = new ArrayList<>();
```

