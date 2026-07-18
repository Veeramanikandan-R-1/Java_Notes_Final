# Java Interview

### Subtopic: Core Java, OOP, Collections, Streams

---

# 1. Fundamentals

Java interview preparation should focus on concepts plus practical tradeoffs.

Core areas:

* OOP
* Object class
* Strings
* Collections
* Exceptions
* Generics
* Streams
* JVM basics

---

# 2. Must-Know Topics

## OOP

Explain encapsulation, inheritance, polymorphism, and abstraction with real examples.

## Collections

Know when to use:

* `ArrayList`
* `HashSet`
* `HashMap`
* `TreeMap`
* `ArrayDeque`

## equals and hashCode

Critical for `HashMap` and `HashSet`.

## Streams

Understand `filter`, `map`, `reduce`, `collect`, grouping, and lazy evaluation.

## Exceptions

Know checked vs unchecked and when to create custom exceptions.

---

# 3. Common Interview Mistakes

* Giving definitions without examples.
* Saying `HashMap` is always O(1) without mentioning collisions.
* Confusing overloading and overriding.
* Not explaining `equals()` and `hashCode()` contract.
* Overusing streams for complex logic.
* Ignoring immutability and thread-safety.

---

# 4. Best Practices

* Answer with definition, example, and tradeoff.
* Use production examples.
* Mention complexity where relevant.
* Explain why, not only what.
* Be honest about edge cases.

---

# 5. Interview Questions with Answers

### 1. ArrayList vs LinkedList?

`ArrayList` uses dynamic array and is better for random access. `LinkedList` uses nodes and has O(n) access. In most backend code, `ArrayList` is preferred.

### 2. HashMap internal working?

HashMap uses `hashCode()` to find bucket and `equals()` to compare keys inside bucket.

### 3. Stream map vs flatMap?

`map()` transforms one element to one value. `flatMap()` flattens nested streams.

### 4. Checked vs unchecked exception?

Checked exceptions are compiler-enforced. Unchecked exceptions extend `RuntimeException`.

---

# Revision Notes

* Explain concepts with examples.
* Collections require implementation tradeoffs.
* `equals()` and `hashCode()` are essential.
* Streams are lazy until terminal operation.
* Prefer immutability for value objects.
* JVM basics include heap, stack, GC, class loading.

---

# Cheat Sheet

| Topic | Must Know |
| ----- | --------- |
| OOP | Four pillars |
| Collections | List/Set/Map tradeoffs |
| HashMap | hashCode + equals |
| Streams | map/filter/reduce |
| Exceptions | checked vs unchecked |
| JVM | heap, stack, GC |

