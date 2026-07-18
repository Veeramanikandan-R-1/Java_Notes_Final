# Generics in Java

### Subtopic: Type Safety

---

# 1. Fundamentals

Generics allow classes, interfaces, and methods to work with types safely.

```java
List<String> names = new ArrayList<>();
names.add("Java");
```

Without generics, values are treated as `Object` and casting is needed.

Generics catch type mistakes at compile time.

---

# 2. Core Concepts

## Generic Class

```java
class Box<T> {
    private T value;

    void set(T value) {
        this.value = value;
    }

    T get() {
        return value;
    }
}
```

Usage:

```java
Box<String> box = new Box<>();
box.set("Java");
```

## Generic Method

```java
public static <T> T first(List<T> values) {
    return values.get(0);
}
```

## Bounded Type

```java
public static <T extends Number> double sum(T a, T b) {
    return a.doubleValue() + b.doubleValue();
}
```

`T` must be `Number` or subclass.

## Wildcards

Unknown type:

```java
List<?> values;
```

Upper bounded wildcard:

```java
void printNumbers(List<? extends Number> numbers) {
}
```

Lower bounded wildcard:

```java
void addIntegers(List<? super Integer> numbers) {
    numbers.add(10);
}
```

Rule:

* `extends` is good for reading.
* `super` is good for writing.

---

# 3. Internal Working

Java generics use type erasure.

Generic type information is mostly removed at compile time and replaced with bounds or `Object`.

```java
List<String>
List<Integer>
```

Both are just `List` at runtime.

This is why you cannot do:

```java
new T();
new List<String>[10];
```

---

# 4. Common Mistakes

* Using raw types like `List`.
* Overusing wildcards.
* Trying to instantiate `T`.
* Assuming generic type is available at runtime.
* Using `List<Object>` when `List<?>` is intended.

Bad:

```java
List list = new ArrayList();
```

Good:

```java
List<String> list = new ArrayList<>();
```

---

# 5. Best Practices

* Use generics for compile-time type safety.
* Avoid raw types.
* Use meaningful type names in complex APIs.
* Use `? extends T` when reading producer data.
* Use `? super T` when writing consumer data.
* Keep public generic APIs simple.

---

# 6. Code Example

```java
class ApiResponse<T> {
    private final T data;
    private final String message;

    ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    T data() {
        return data;
    }
}
```

Usage:

```java
ApiResponse<UserDto> response = new ApiResponse<>(user, "success");
```

---

# 7. Real-world Scenarios

* Collections.
* API response wrappers.
* Repository base interfaces.
* Utility methods.
* Event payloads.
* Type-safe service results.

---

# Revision Notes

* Generics provide compile-time type safety.
* `T` is a type parameter.
* Generic methods define type before return type.
* Bounds restrict allowed types.
* Java generics use type erasure.
* Avoid raw types.
* `? extends T` is for producers.
* `? super T` is for consumers.

---

# Cheat Sheet

| Need | Syntax |
| ---- | ------ |
| Generic class | `class Box<T>` |
| Generic method | `<T> T first(...)` |
| Upper bound | `<T extends Number>` |
| Unknown | `List<?>` |
| Read numbers | `List<? extends Number>` |
| Add integers | `List<? super Integer>` |

---

# Interview Questions with Answers

### 1. Why use generics?

To catch type errors at compile time and avoid unsafe casting.

### 2. What is type erasure?

The compiler removes most generic type information at runtime.

### 3. Difference between `List<?>` and `List<Object>`?

`List<?>` can reference a list of any type. `List<Object>` only accepts lists actually typed as Object.

---

# Hands-on Exercises

### Exercise 1

Create generic `Pair`.

**Answer**

```java
class Pair<K, V> {
    private final K key;
    private final V value;

    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
```

