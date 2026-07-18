# Cascade Operations

### Subtopic: Cascade Types, Orphan Removal

---

# 1. Fundamentals

Cascade controls whether an operation on a parent entity is propagated to related child entities.

```java
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
private List<OrderItem> items = new ArrayList<>();
```

Use cascade when child lifecycle truly belongs to the parent.

---

# 2. Core Concepts

## Cascade Types

| Type | Meaning |
| ---- | ------- |
| `PERSIST` | Persist child when parent is persisted |
| `MERGE` | Merge child when parent is merged |
| `REMOVE` | Remove child when parent is removed |
| `REFRESH` | Refresh child from database |
| `DETACH` | Detach child from persistence context |
| `ALL` | All cascade operations |

## Orphan Removal

`orphanRemoval = true` deletes a child when it is removed from the parent collection.

```java
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
private List<OrderItem> items = new ArrayList<>();
```

This is not the same as `CascadeType.REMOVE`. `REMOVE` deletes children when parent is deleted. Orphan removal deletes children removed from the relationship.

---

# 3. Internal Working

Hibernate compares collection snapshots during flush. If a child disappeared from an orphan-removal collection, Hibernate schedules a delete.

Cascades are applied through entity state transitions. For example, persisting an `Order` can cascade persist to new `OrderItem` objects.

---

# 4. Common Mistakes

* Using `CascadeType.ALL` everywhere.
* Using `REMOVE` on many-to-many relationships.
* Expecting cascade to work from inverse side only.
* Replacing collections and confusing orphan tracking.
* Using orphan removal for shared children.
* Forgetting database foreign key constraints.

---

# 5. Best Practices

* Cascade only across ownership boundaries.
* Use orphan removal for true parent-child composition.
* Avoid remove cascade for shared references.
* Mutate existing collections through helper methods.
* Keep both sides of bidirectional relationships synchronized.
* Test delete behavior carefully.

---

# 6. Code Example

```java
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null);
    }
}
```

---

# 7. Real-world Scenarios

* Order owns order items.
* Cart owns cart items.
* User owns user settings.
* Invoice owns invoice lines.
* Role does not own users in many-to-many.

---

# Revision Notes

* Cascade propagates parent operations to children.
* Use cascade only when lifecycle is owned.
* `orphanRemoval` deletes children removed from parent collection.
* `CascadeType.REMOVE` deletes children when parent is removed.
* Avoid remove cascade on many-to-many.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Persist child with parent | `PERSIST` |
| Update detached graph | `MERGE` |
| Delete children with parent | `REMOVE` |
| All operations | `ALL` |
| Delete removed child | `orphanRemoval = true` |
| Shared child | Avoid remove cascade |

---

# Interview Questions with Answers

### 1. What is cascade?

Propagation of entity operations from one entity to related entities.

### 2. Cascade remove vs orphan removal?

Cascade remove deletes children when parent is deleted. Orphan removal deletes children removed from the parent relationship.

### 3. Why is remove cascade dangerous in many-to-many?

Because the target entity may be linked to other parents.

---

# Hands-on Exercises

### Exercise 1

Configure order items to be persisted and deleted with order.

**Answer**

```java
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
private List<OrderItem> items = new ArrayList<>();
```

