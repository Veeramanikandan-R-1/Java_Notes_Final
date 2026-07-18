# Revision Notes

* Integration tests verify multiple application parts working together.
* `@SpringBootTest` loads the full Spring context.
* `@WebMvcTest` focuses on controller and MVC behavior.
* `@DataJpaTest` focuses on JPA repositories.
* `@MockBean` replaces a Spring bean in the context.
* Spring caches similar test contexts for speed.
* Use profiles such as `test` to avoid unsafe production configuration.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Full app context | `@SpringBootTest` |
| Controller slice | `@WebMvcTest` |
| Repository slice | `@DataJpaTest` |
| HTTP-style tests | `MockMvc` |
| Replace bean | `@MockBean` |
| Test properties | `@ActiveProfiles("test")` |

---

# Interview Q&A

### 1. Unit test vs integration test?

Unit tests isolate one class. Integration tests verify connected components.

### 2. Why not use `@SpringBootTest` everywhere?

It is slower and often loads far more application context than needed.

### 3. Why can H2 be risky?

It may not match production database SQL, types, indexes, and locking behavior.

---

# Exercises

### Exercise 1

Write a `MockMvc` test for `400 Bad Request`.

### Exercise 2

Write a `@DataJpaTest` for a custom repository query.

