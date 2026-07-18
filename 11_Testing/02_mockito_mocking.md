# Mockito

### Subtopic: Mocking

---

# 1. Fundamentals

Mockito is a Java mocking framework used to isolate the class under test from its collaborators.

A mock is a fake implementation controlled by the test.

Use Mockito when a class depends on:

* Repository interfaces
* External API clients
* Message publishers
* Email/SMS gateways
* Payment gateways
* Clock-like collaborators

Mockito is common in service-layer unit tests where you want to test business logic without starting Spring or touching infrastructure.

---

# 2. Core Concepts

## Mock

```java
OrderRepository repository = mock(OrderRepository.class);
```

The mock does not run real database code.

---

## Stubbing

Stubbing defines what a mock should return.

```java
when(repository.findById("o1"))
    .thenReturn(Optional.of(new Order("o1", "PAID")));
```

---

## Verification

Verification checks that an interaction happened.

```java
verify(repository).save(order);
```

Use verification for important side effects, not every internal call.

---

# 3. Mockito with JUnit 5

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @Mock
    private PaymentClient paymentClient;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldMarkOrderPaidAfterSuccessfulPayment() {
        Order order = new Order("o1", "NEW");
        when(repository.findById("o1")).thenReturn(Optional.of(order));
        when(paymentClient.charge("o1")).thenReturn(true);

        orderService.pay("o1");

        verify(repository).save(new Order("o1", "PAID"));
    }
}
```

`@InjectMocks` creates the class under test and injects mocks into it.

---

# 4. Internal Working

Mockito creates runtime proxy objects.

When a method is called on a mock:

1. Mockito records the invocation.
2. It checks whether a stub matches the method call.
3. If a stub exists, it returns the configured value.
4. If no stub exists, it returns a default value.

Default values:

| Return Type | Default |
| ----------- | ------- |
| `boolean` | `false` |
| numeric primitives | `0` |
| object | `null` |
| collection-like common types | often empty depending on Mockito version |

Do not rely heavily on defaults. Explicit stubbing makes tests clearer.

---

# 5. Argument Matchers

```java
when(paymentClient.charge(anyString(), anyInt()))
    .thenReturn(PaymentResult.success());

verify(repository).findByCustomerId(eq("c1"));
```

Common matchers:

| Matcher | Meaning |
| ------- | ------- |
| `any()` | Any object |
| `anyString()` | Any string |
| `eq(value)` | Exact value |
| `argThat(...)` | Custom condition |

If one argument uses a matcher, all arguments in that call should use matchers.

---

# 6. Capturing Arguments

Use `ArgumentCaptor` when the saved object is built inside the method.

```java
ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);

service.createOrder("c1", "p1");

verify(repository).save(captor.capture());
Order saved = captor.getValue();
assertEquals("c1", saved.customerId());
assertEquals("p1", saved.productId());
```

---

# 7. Common Mistakes

* Mocking the class being tested.
* Over-mocking simple value objects.
* Verifying every method call instead of final behavior.
* Using Mockito for integration tests where real wiring is required.
* Stubbing methods that are never used.
* Testing the mock instead of testing production logic.
* Mixing Spring `@MockBean` and Mockito `@Mock` without understanding the test type.

---

# 8. Best Practices

* Mock external dependencies, not domain objects.
* Prefer constructor injection in production code.
* Keep one class under test.
* Stub only what the test needs.
* Verify side effects such as `save`, `publish`, or `send`.
* Use `ArgumentCaptor` for complex generated arguments.
* Use integration tests when you need Spring configuration, transactions, or database behavior.

---

# 9. Production Backend Example

```java
class UserRegistrationService {
    private final UserRepository userRepository;
    private final EmailGateway emailGateway;

    UserRegistrationService(UserRepository userRepository, EmailGateway emailGateway) {
        this.userRepository = userRepository;
        this.emailGateway = emailGateway;
    }

    void register(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("email already registered");
        }

        User user = new User(email);
        userRepository.save(user);
        emailGateway.sendWelcomeEmail(email);
    }
}
```

```java
@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceTest {

    @Mock UserRepository userRepository;
    @Mock EmailGateway emailGateway;

    @InjectMocks UserRegistrationService service;

    @Test
    void shouldSendWelcomeEmailAfterRegistration() {
        when(userRepository.existsByEmail("a@test.com")).thenReturn(false);

        service.register("a@test.com");

        verify(userRepository).save(any(User.class));
        verify(emailGateway).sendWelcomeEmail("a@test.com");
    }

    @Test
    void shouldRejectDuplicateEmail() {
        when(userRepository.existsByEmail("a@test.com")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.register("a@test.com"));
        verify(emailGateway, never()).sendWelcomeEmail(anyString());
    }
}
```

---

# 10. Real-world Scenarios

* Test service logic without a database.
* Verify event publishing after a state change.
* Simulate payment gateway failures.
* Ensure emails are not sent when validation fails.
* Test retry decision logic without waiting or calling external systems.

---

# Revision Notes

* Mockito isolates the class under test from collaborators.
* `mock()` creates a fake implementation.
* `when(...).thenReturn(...)` stubs behavior.
* `verify(...)` checks interactions.
* `@Mock` creates mocks with JUnit 5 extension.
* `@InjectMocks` injects mocks into the class under test.
* `ArgumentCaptor` inspects arguments built inside production code.

---

# Cheat Sheet

| Need | Mockito |
| ---- | ------- |
| Enable Mockito | `@ExtendWith(MockitoExtension.class)` |
| Create mock | `@Mock` |
| Inject mocks | `@InjectMocks` |
| Stub | `when(x).thenReturn(y)` |
| Verify call | `verify(mock).method()` |
| Verify not called | `verify(mock, never()).method()` |
| Match any string | `anyString()` |
| Capture argument | `ArgumentCaptor` |

---

# Interview Questions with Answers

### 1. What is mocking?

Mocking replaces a real dependency with a controlled fake object so the test can focus on one class.

### 2. What is the difference between stubbing and verification?

Stubbing defines what a mock returns. Verification checks whether a mock was called.

### 3. When should Mockito not be used?

When you need to verify real framework wiring, database behavior, transactions, serialization, or security filters.

### 4. What is `ArgumentCaptor`?

It captures an argument passed to a mock so the test can inspect its values.

---

# Hands-on Exercises

### Exercise 1

Mock a repository so `findById("u1")` returns a user.

**Answer**

```java
when(userRepository.findById("u1"))
    .thenReturn(Optional.of(new User("u1")));
```

### Exercise 2

Verify that `emailGateway.sendWelcomeEmail(...)` is not called when registration fails.

