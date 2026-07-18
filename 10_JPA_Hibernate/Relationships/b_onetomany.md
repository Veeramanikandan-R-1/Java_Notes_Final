# Relationships

### Subtopic: OneToMany

---

# 1. Fundamentals

`@OneToMany` maps one parent entity to multiple child entities.

Example: one `Order` has many `OrderItem` rows.

---

# 2. Core Concepts

## Recommended Mapping

The most common production mapping is bidirectional:

```java
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
private List<OrderItem> items = new ArrayList<>();
```

Child owns the foreign key:

```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "order_id", nullable = false)
private Order order;
```

## Why Child Owns the Relationship

The foreign key usually lives in the many-side table. So the `OrderItem` table has `order_id`.

## Helper Methods

Bidirectional relationships must be synchronized in Java.

```java
public void addItem(OrderItem item) {
    items.add(item);
    item.setOrder(this);
}
```

---

# 3. Internal Working

Hibernate tracks collection changes. With `orphanRemoval = true`, removing a child from the parent collection can delete the child row.

Without proper helper methods, Java object state and database relationship state can drift apart.

---

# 4. Common Mistakes

* Using unidirectional `@OneToMany` with join table unintentionally.
* Forgetting helper methods.
* Replacing collections instead of mutating them.
* Using eager loading on large collections.
* Returning entities and triggering lazy loading during JSON serialization.
* Using `CascadeType.REMOVE` where children should not be deleted.

---

# 5. Best Practices

* Prefer bidirectional `@OneToMany` with `@ManyToOne` owning side.
* Use `List` when ordering matters and `Set` when uniqueness matters.
* Keep collections initialized.
* Add helper methods for add/remove.
* Keep large collections lazy.
* Use DTO queries for read-heavy screens.

---

# 6. Code Example

```java
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
        item.setOrder(null);
    }
}

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    void setOrder(Order order) {
        this.order = order;
    }
}
```

---

# 7. Real-world Scenarios

* Order to order items.
* Customer to addresses.
* Blog post to comments.
* Department to employees.

---

# Revision Notes

* One-to-many maps one parent to many children.
* The many side usually owns the foreign key.
* Parent uses `mappedBy`.
* Child uses `@ManyToOne` and `@JoinColumn`.
* Use helper methods to sync both sides.
* Keep collections lazy.

---

# Cheat Sheet

| Need | Mapping |
| ---- | ------- |
| Parent collection | `@OneToMany(mappedBy = "...")` |
| Child reference | `@ManyToOne` |
| FK column | `@JoinColumn` on child |
| Delete removed child | `orphanRemoval = true` |
| Persist children with parent | `CascadeType.PERSIST` or `ALL` |

---

# Interview Questions with Answers

### 1. Which side owns one-to-many?

Usually the many side, because it contains the foreign key.

### 2. Why use helper methods?

They keep both Java object references consistent.

### 3. Why avoid eager one-to-many?

Large collections can create heavy joins, memory usage, and N plus one issues.

---

# Hands-on Exercises

### Exercise 1

Map `Department` to many `Employee` entities.

**Answer**

```java
@OneToMany(mappedBy = "department")
private List<Employee> employees = new ArrayList<>();
```

