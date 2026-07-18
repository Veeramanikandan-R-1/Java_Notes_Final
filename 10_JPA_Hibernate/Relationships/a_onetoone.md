# Relationships

### Subtopic: OneToOne

---

# 1. Fundamentals

`@OneToOne` maps one row of an entity to one row of another entity.

Example: one `User` has one `UserProfile`.

---

# 2. Core Concepts

## Owning Side

The owning side contains the foreign key and uses `@JoinColumn`.

```java
@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "profile_id")
private UserProfile profile;
```

## Inverse Side

The inverse side uses `mappedBy`.

```java
@OneToOne(mappedBy = "profile")
private User user;
```

## Shared Primary Key

Sometimes the child uses the same primary key as the parent.

```java
@OneToOne
@MapsId
@JoinColumn(name = "user_id")
private User user;
```

This is useful when profile cannot exist without user.

---

# 3. Internal Working

Hibernate uses a foreign key to connect the two tables. The owning side controls updates to that foreign key.

Lazy loading for one-to-one can be tricky because Hibernate may need bytecode enhancement or nullable checks to know whether a proxy can be used.

---

# 4. Common Mistakes

* Putting `@JoinColumn` on the wrong side.
* Forgetting to keep both sides of a bidirectional relationship in sync.
* Using eager loading by default and creating unnecessary joins.
* Modeling one-to-one when the data could be columns in the same table.
* Cascading remove when the child is shared or should survive.

---

# 5. Best Practices

* Prefer unidirectional one-to-one unless bidirectional navigation is needed.
* Put foreign key on the table that naturally depends on the other.
* Use `fetch = FetchType.LAZY` and verify generated SQL.
* Use `@MapsId` for strict dependent child rows.
* Add unique constraint to enforce one-to-one at database level.

---

# 6. Code Example

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserProfile profile;

    public void setProfile(UserProfile profile) {
        this.profile = profile;
        profile.setUser(this);
    }
}

@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    void setUser(User user) {
        this.user = user;
    }
}
```

---

# 7. Real-world Scenarios

* User and profile.
* Employee and identity document.
* Order and invoice.
* Customer and preference settings.

---

# Revision Notes

* One-to-one maps one entity row to one related row.
* Owning side has `@JoinColumn`.
* Inverse side uses `mappedBy`.
* Database should enforce uniqueness.
* `@MapsId` is useful for dependent child rows.

---

# Cheat Sheet

| Need | Mapping |
| ---- | ------- |
| Owning side | `@OneToOne` + `@JoinColumn` |
| Inverse side | `mappedBy` |
| Shared PK | `@MapsId` |
| Dependent child | `cascade` + `orphanRemoval` |
| Performance | Prefer `LAZY` |

---

# Interview Questions with Answers

### 1. Which side owns a one-to-one relationship?

The side with the foreign key and `@JoinColumn`.

### 2. Why use `unique = true`?

To enforce that one parent row maps to only one child row.

### 3. What does `mappedBy` mean?

It says the relationship is controlled by the field on the other entity.

---

# Hands-on Exercises

### Exercise 1

Map `Employee` to one `EmployeeAddress`.

**Answer**

```java
@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinColumn(name = "address_id", unique = true)
private EmployeeAddress address;
```

