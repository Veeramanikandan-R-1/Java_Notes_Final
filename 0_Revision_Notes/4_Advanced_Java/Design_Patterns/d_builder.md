# Revision Notes

* Builder creates complex objects step by step.
* Useful when constructors have many optional fields.
* Improves readability.
* Supports immutable objects.
* Validate required fields in `build()`.
* Avoid builder for very simple objects.

---

# Cheat Sheet

| Need | Builder Benefit |
| ---- | --------------- |
| Many fields | Readability |
| Optional fields | Fluent setup |
| Immutable result | Final assignment |
| Validation | `build()` |
| Test data | Easy object setup |

---

# Interview Questions with Answers

### 1. What problem does Builder solve?

It avoids long constructors and makes complex object creation readable.

### 2. Builder vs Factory?

Builder constructs step by step. Factory chooses which object to create.

### 3. Why is Builder useful for immutable objects?

Builder collects values first, then creates an object with final fields.

