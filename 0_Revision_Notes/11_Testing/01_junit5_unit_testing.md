# Revision Notes

* JUnit 5 is the standard unit testing framework for modern Java.
* Unit tests should be fast, isolated, deterministic, and behavior-focused.
* Use Arrange Act Assert for readable tests.
* `@BeforeEach` prepares fresh state before every test.
* `assertThrows` verifies exception behavior.
* Parameterized tests avoid duplicate test methods for input variations.
* Inject `Clock` for time-dependent code.

---

# Cheat Sheet

| Need | JUnit 5 |
| ---- | ------- |
| Test method | `@Test` |
| Setup | `@BeforeEach` |
| Assert equality | `assertEquals(expected, actual)` |
| Assert exception | `assertThrows(...)` |
| Multiple inputs | `@ParameterizedTest` |
| Run Maven tests | `mvn test` |

---

# Interview Q&A

### 1. What is a unit test?

A fast isolated test that verifies one small behavior, usually in one class or method.

### 2. Why avoid database calls in unit tests?

They make tests slower and less isolated. Use integration tests for database behavior.

### 3. Why is deterministic testing important?

A test should pass or fail for code reasons, not because of time, order, randomness, or environment.

---

# Exercises

### Exercise 1

Use `assertThrows` to test invalid input.

### Exercise 2

Convert three duplicate input tests into one parameterized test.

