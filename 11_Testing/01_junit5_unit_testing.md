# JUnit5

### Subtopic: Unit Testing

---

# 1. Fundamentals

JUnit 5 is the standard testing framework for modern Java applications.

A unit test verifies a small piece of code in isolation, usually one method or one class.

Good unit tests are:

* Fast
* Deterministic
* Independent
* Easy to read
* Focused on behavior

In backend systems, unit tests are most useful for service logic, validators, mappers, utility classes, pricing rules, permission decisions, and error handling.

---

# 2. Core Concepts

## Test Method

```java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceCalculatorTest {

    @Test
    void shouldApplyTenPercentDiscount() {
        PriceCalculator calculator = new PriceCalculator();

        int result = calculator.discountedPrice(100, 10);

        assertEquals(90, result);
    }
}
```

Naming should explain behavior, not implementation.

Good names:

```text
shouldRejectExpiredCoupon
shouldReturnEmptyListWhenUserHasNoOrders
shouldThrowExceptionForNegativeAmount
```

---

## Assertions

Common assertions:

```java
assertEquals(expected, actual);
assertTrue(condition);
assertFalse(condition);
assertNull(value);
assertNotNull(value);
assertThrows(IllegalArgumentException.class, () -> service.create(null));
```

Use the most specific assertion possible.

---

## Lifecycle Methods

```java
@BeforeEach
void setUp() {
    service = new OrderService(repository);
}
```

Useful annotations:

| Annotation | Meaning |
| ---------- | ------- |
| `@Test` | Marks a test method |
| `@BeforeEach` | Runs before every test |
| `@AfterEach` | Runs after every test |
| `@BeforeAll` | Runs once before all tests |
| `@AfterAll` | Runs once after all tests |
| `@Disabled` | Temporarily disables a test |

---

## Arrange Act Assert

Use a clear test structure:

```java
@Test
void shouldCreateOrderWhenStockIsAvailable() {
    // Arrange
    Product product = new Product("p1", 5);
    OrderService service = new OrderService();

    // Act
    Order order = service.createOrder(product, 2);

    // Assert
    assertEquals("p1", order.productId());
    assertEquals(2, order.quantity());
}
```

---

# 3. Internal Working

JUnit discovers tests from the classpath using the JUnit Platform.

Important pieces:

| Part | Role |
| ---- | ---- |
| JUnit Platform | Launches test execution |
| JUnit Jupiter | JUnit 5 programming model |
| Test Engine | Executes discovered tests |

Build tools like Maven and Gradle run JUnit through plugins.

Maven:

```bash
mvn test
```

Gradle:

```bash
gradle test
```

---

# 4. Parameterized Tests

Use parameterized tests when the same behavior must be checked with multiple inputs.

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxCalculatorTest {

    @ParameterizedTest
    @CsvSource({
        "100, 18, 118",
        "200, 5, 210",
        "0, 18, 0"
    })
    void shouldCalculateTax(int price, int taxPercent, int expected) {
        TaxCalculator calculator = new TaxCalculator();

        assertEquals(expected, calculator.addTax(price, taxPercent));
    }
}
```

---

# 5. Testing Exceptions

```java
@Test
void shouldRejectNegativeAmount() {
    PaymentService service = new PaymentService();

    IllegalArgumentException ex = assertThrows(
        IllegalArgumentException.class,
        () -> service.pay(-100)
    );

    assertEquals("amount must be positive", ex.getMessage());
}
```

Do not only test the happy path. Error paths are often where production bugs hide.

---

# 6. Common Mistakes

* Testing private methods directly instead of public behavior.
* Writing tests that depend on execution order.
* Putting database, network, or file system calls in unit tests.
* Using vague assertions like `assertNotNull` when exact output is known.
* Testing implementation details instead of business rules.
* Creating one huge test that checks many unrelated things.
* Ignoring edge cases such as empty input, null input, duplicates, time zones, and overflow.

---

# 7. Best Practices

* Keep unit tests small and fast.
* Use readable test data.
* Test observable behavior.
* Prefer constructor injection in production code because it makes testing easier.
* Keep one reason for a test to fail.
* Use parameterized tests for input variations.
* Avoid sleeping in tests.
* Avoid randomness unless controlled with a fixed seed.
* Make time testable by injecting `Clock`.

---

# 8. Production Backend Example

```java
import java.time.Clock;
import java.time.LocalDate;

class SubscriptionService {
    private final Clock clock;

    SubscriptionService(Clock clock) {
        this.clock = clock;
    }

    boolean isExpired(LocalDate expiryDate) {
        LocalDate today = LocalDate.now(clock);
        return expiryDate.isBefore(today);
    }
}
```

```java
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SubscriptionServiceTest {

    @Test
    void shouldReturnExpiredWhenExpiryDateIsBeforeToday() {
        Clock fixedClock = Clock.fixed(
            Instant.parse("2026-01-10T00:00:00Z"),
            ZoneOffset.UTC
        );
        SubscriptionService service = new SubscriptionService(fixedClock);

        assertTrue(service.isExpired(LocalDate.of(2026, 1, 9)));
    }
}
```

---

# 9. Real-world Scenarios

* Validate order totals and discount rules.
* Test role-based decisions before adding Spring Security integration tests.
* Verify mapping from DTO to entity.
* Test retry decision logic without calling external services.
* Test fraud, billing, tax, inventory, and SLA calculations.

---

# Revision Notes

* JUnit 5 is used for Java unit testing.
* Unit tests should be fast, isolated, deterministic, and focused.
* Use Arrange Act Assert for readability.
* `assertThrows` verifies exception behavior.
* Parameterized tests reduce duplicate tests.
* Inject time using `Clock` to avoid flaky date tests.
* Unit tests should not require Spring context, database, or network.

---

# Cheat Sheet

| Need | JUnit 5 |
| ---- | ------- |
| Test method | `@Test` |
| Setup before each test | `@BeforeEach` |
| Expected value | `assertEquals(expected, actual)` |
| Exception | `assertThrows(...)` |
| Multiple inputs | `@ParameterizedTest` |
| Disable test | `@Disabled` |
| Run with Maven | `mvn test` |

---

# Interview Questions with Answers

### 1. What makes a good unit test?

A good unit test is fast, isolated, deterministic, readable, and verifies one behavior clearly.

### 2. Why should unit tests avoid the database?

Because database calls make tests slower, harder to isolate, and more fragile. Database behavior should be covered by integration tests.

### 3. What is `assertThrows` used for?

It verifies that a specific operation throws an expected exception.

### 4. Why inject `Clock`?

It makes time-dependent code testable without depending on the machine's current date and time.

---

# Hands-on Exercises

### Exercise 1

Write a test for a method that rejects negative quantity.

**Answer**

```java
@Test
void shouldRejectNegativeQuantity() {
    InventoryService service = new InventoryService();

    assertThrows(IllegalArgumentException.class, () -> service.reserve("p1", -1));
}
```

### Exercise 2

Create a parameterized test for discount percentages `0`, `10`, and `50`.

