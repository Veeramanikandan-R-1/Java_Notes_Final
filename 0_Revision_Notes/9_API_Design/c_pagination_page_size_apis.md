# Revision Notes

* Pagination splits large collections into controlled pages.
* `page` selects the slice and `size` controls result count.
* Spring Data pages are zero-based by default.
* `Page<T>` includes total count metadata.
* `Slice<T>` avoids total count and is useful for infinite scrolling.
* Always enforce maximum size.
* Always use stable sorting.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| First page | `page=0` |
| Count per page | `size=20` |
| Max limit | Server validation |
| Spring paging | `Pageable` |
| Total count | `Page<T>` |
| No total count | `Slice<T>` |

---

# Interview Questions with Answers

### 1. Why paginate?

To avoid large responses, slow queries, and memory pressure.

### 2. Why cap page size?

To protect the database and API from abusive or accidental large requests.

### 3. Why stable sort?

It prevents duplicates and missing records across pages.

---

# Hands-on Exercises

### Exercise 1

Request the third page with 20 records per page.

**Answer**

```http
GET /products?page=2&size=20
```

