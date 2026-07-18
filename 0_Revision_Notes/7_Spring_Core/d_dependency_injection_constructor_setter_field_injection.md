# Revision Notes

* Constructor injection is preferred for mandatory dependencies.
* Setter injection is useful for optional dependencies.
* Field injection is concise but harder to test.
* Constructor injection supports immutability.
* Circular dependencies are easier to detect with constructor injection.
* Avoid hiding dependencies.

---

# Cheat Sheet

| Injection | Best For |
| --------- | -------- |
| Constructor | Required dependencies |
| Setter | Optional dependencies |
| Field | Avoid in production code |
| `@Autowired` | Dependency injection |

---

# Interview Questions with Answers

### 1. Which injection is preferred?

Constructor injection.

### 2. Why avoid field injection?

It hides dependencies, makes testing harder, and prevents final fields.

### 3. When use setter injection?

For optional dependencies or reconfigurable collaborators.

