# Revision Notes

* Variables are named storage locations.
* Primitives store values directly.
* References store object addresses.
* Stack stores local variables.
* Heap stores objects.
* Wrapper classes enable object behavior.
* Autoboxing/unboxing can introduce performance and NPE issues.
* Use Long for IDs.
* Use BigDecimal for money.
* Prefer primitives when null is unnecessary.
* Understand memory implications of every type choice.

---

# Cheat Sheet

| Category        | Type                                                          |
| --------------- | ------------------------------------------------------------- |
| Integer         | byte, short, int, long                                        |
| Decimal         | float, double                                                 |
| Character       | char                                                          |
| Boolean         | boolean                                                       |
| Object Versions | Byte, Short, Integer, Long, Float, Double, Character, Boolean |

### Production Mapping

| Java       | Database |
| ---------- | -------- |
| Long       | BIGINT   |
| Integer    | INT      |
| String     | VARCHAR  |
| Boolean    | BOOLEAN  |
| BigDecimal | DECIMAL  |

---

# Interview Questions / Flashcards

### Q1: Difference between primitive and reference types?

**Answer:** Primitives store actual values. References store object references pointing to heap memory.

---

### Q2: Why is Integer slower than int?

**Answer:** Integer is an object requiring heap allocation, indirection, and GC tracking.

---

### Q3: Why should money never use double?

**Answer:** Binary floating-point representation introduces precision errors.

---

### Q4: Where are local variables stored?

**Answer:** Stack frame of the executing thread.

---

### Q5: Where are objects stored?

**Answer:** Heap memory.

---

### Q6: What is autoboxing?

**Answer:** Automatic conversion of primitive to wrapper type.

```java
Integer x = 10;
```

becomes:

```java
Integer x = Integer.valueOf(10);
```

---

### Q7: Why are wrapper classes required in collections?

**Answer:** Java generics work only with reference types.

---

### Q8: Why is Long preferred for IDs?

**Answer:** Supports much larger ranges and aligns with BIGINT database columns.