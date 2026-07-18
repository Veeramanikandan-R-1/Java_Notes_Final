# Revision Notes

* N plus one means 1 parent query plus N child queries.
* It often appears when lazy associations are accessed in loops.
* Eager loading is not a general fix.
* Use fetch join when association is required.
* Use DTO projection for read-only list pages.
* Use entity graphs for declarative fetch plans.
* Use batch fetching to reduce repeated lazy queries.
* Confirm with SQL logs or query count tests.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Fetch relation now | `join fetch` |
| Read-only list | DTO projection |
| Declarative plan | `@EntityGraph` |
| Reduce lazy queries | `@BatchSize` |
| Detect | SQL logs |
| Avoid | Blind eager loading |

---

# Interview Questions with Answers

### 1. What is N plus one?

One query loads N parent rows, then N more queries load related data.

### 2. How detect it?

Inspect SQL logs, use datasource proxy, or assert query counts in tests.

### 3. Why use DTO projection?

It fetches only required fields and avoids unnecessary entity graph loading.

---

# Hands-on Exercises

### Exercise 1

Fetch posts with authors in one query.

**Answer**

```java
@Query("select p from Post p join fetch p.author")
List<Post> findAllWithAuthor();
```

