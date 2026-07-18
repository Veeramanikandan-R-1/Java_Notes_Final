# Revision Notes

* Mockito creates controlled fake collaborators for unit tests.
* Mock dependencies, not the class under test.
* Stubbing defines mock return values.
* Verification checks important interactions.
* `@Mock` creates a mock with Mockito extension.
* `@InjectMocks` creates the class under test and injects mocks.
* `ArgumentCaptor` inspects arguments passed to mocks.

---

# Cheat Sheet

| Need | Mockito |
| ---- | ------- |
| Enable | `@ExtendWith(MockitoExtension.class)` |
| Mock field | `@Mock` |
| Inject mocks | `@InjectMocks` |
| Stub | `when(...).thenReturn(...)` |
| Verify | `verify(mock).method()` |
| Verify never | `verify(mock, never()).method()` |
| Capture | `ArgumentCaptor` |

---

# Interview Q&A

### 1. What is a mock?

A fake dependency controlled by the test.

### 2. What is stubbing?

Configuring what a mock returns for a method call.

### 3. When is Mockito a poor fit?

When testing real Spring wiring, database behavior, transactions, or framework configuration.

---

# Exercises

### Exercise 1

Stub a repository to return `Optional.of(user)`.

### Exercise 2

Verify that an email gateway is not called after validation fails.

