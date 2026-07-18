# Revision Notes

* Generics make code type-safe.
* Type errors are caught at compile time.
* Generic classes use type parameters like `T`.
* Generic methods declare type before return type.
* Bounds restrict generic types.
* Java generics use type erasure.
* Avoid raw types.
* Use `extends` wildcard for reading.
* Use `super` wildcard for writing.

---

# Cheat Sheet

| Concept | Code |
| ------- | ---- |
| Generic class | `class Box<T>` |
| Generic method | `<T> T get(T value)` |
| Upper bound | `<T extends Number>` |
| Wildcard | `List<?>` |
| Producer | `? extends T` |
| Consumer | `? super T` |

---

# Interview Questions with Answers

### 1. What problem do generics solve?

They provide type safety and remove unnecessary casting.

### 2. What is raw type?

Using a generic type without type parameter, such as `List` instead of `List<String>`.

### 3. Can you create `new T()`?

No, because generic type information is erased at runtime.

---

# Hands-on Exercises

### Exercise 1

Write generic method to return first element.

**Answer**

```java
public static <T> T first(List<T> values) {
    return values.get(0);
}
```

