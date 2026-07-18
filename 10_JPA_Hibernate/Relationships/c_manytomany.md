# Relationships

### Subtopic: ManyToMany

---

# 1. Fundamentals

`@ManyToMany` maps many rows of one entity to many rows of another entity.

Example: many students can enroll in many courses.

It uses a join table.

---

# 2. Core Concepts

## Direct Many-to-Many

```java
@ManyToMany
@JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
)
private Set<Course> courses = new HashSet<>();
```

## Owning Side

The owning side defines `@JoinTable`. The other side uses `mappedBy`.

## Join Entity Alternative

When the association has extra columns, use an entity instead of direct many-to-many.

```text
Student -> Enrollment -> Course
```

This is better for fields like enrolled date, grade, status, or audit data.

---

# 3. Internal Working

Hibernate persists links in the join table. Removing an item from the collection deletes the join row, not necessarily the target entity.

`CascadeType.REMOVE` is dangerous in many-to-many because the target entity may be shared by other parents.

---

# 4. Common Mistakes

* Using `List` and causing duplicate join rows.
* Using `CascadeType.REMOVE` between shared entities.
* Direct many-to-many when the join table needs business fields.
* Eager loading both sides.
* Serializing bidirectional many-to-many entities directly.
* Forgetting indexes and unique constraints on the join table.

---

# 5. Best Practices

* Prefer `Set` for many-to-many collections.
* Avoid `CascadeType.REMOVE`.
* Use a join entity for real business associations.
* Keep many-to-many lazy.
* Add unique constraint for pair columns.
* Keep API responses DTO-based.

---

# 6. Code Example

```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    public void enroll(Course course) {
        courses.add(course);
        course.getStudents().add(this);
    }
}

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    public Set<Student> getStudents() {
        return students;
    }
}
```

---

# 7. Real-world Scenarios

* Students and courses.
* Users and roles.
* Products and tags.
* Books and authors.

---

# Revision Notes

* Many-to-many uses a join table.
* Owning side defines `@JoinTable`.
* Inverse side uses `mappedBy`.
* Prefer `Set`.
* Avoid `CascadeType.REMOVE`.
* Use join entity when relationship has extra fields.

---

# Cheat Sheet

| Need | Mapping |
| ---- | ------- |
| Direct relation | `@ManyToMany` |
| Join table | `@JoinTable` |
| Other side | `mappedBy` |
| Extra columns | Join entity |
| Safe collection | `Set` |
| Dangerous cascade | `REMOVE` |

---

# Interview Questions with Answers

### 1. When should direct many-to-many be avoided?

When the join table has business fields or lifecycle rules.

### 2. Why is `CascadeType.REMOVE` dangerous?

The related entity may be used by other parents and should not be deleted.

### 3. What does the join table store?

Pairs of foreign keys linking the two entity tables.

---

# Hands-on Exercises

### Exercise 1

Model users and roles using direct many-to-many.

**Answer**

```java
@ManyToMany
@JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
)
private Set<Role> roles = new HashSet<>();
```

