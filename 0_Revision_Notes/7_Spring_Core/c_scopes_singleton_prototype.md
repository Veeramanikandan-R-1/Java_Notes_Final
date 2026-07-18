# Revision Notes

* Bean scope controls bean instance lifecycle.
* Singleton is default scope in Spring.
* Singleton means one bean instance per Spring container.
* Prototype creates new instance each time requested.
* Singleton beans should avoid unsafe mutable shared state.
* Prototype destruction is not fully managed like singleton.

---

# Cheat Sheet

| Scope | Meaning |
| ----- | ------- |
| Singleton | One instance per container |
| Prototype | New instance per request |
| Default | Singleton |
| Annotation | `@Scope("prototype")` |

---

# Interview Questions with Answers

### 1. What is default Spring bean scope?

Singleton.

### 2. Singleton in Spring vs Singleton pattern?

Spring singleton is one instance per container, not necessarily one per JVM.

### 3. When use prototype?

When each request for the bean needs a fresh object instance.

