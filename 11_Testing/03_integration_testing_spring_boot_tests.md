# Integration Testing

### Subtopic: Spring Boot Tests

---

# 1. Fundamentals

Integration tests verify that multiple parts of the application work together.

In Spring Boot, integration tests commonly verify:

* Controller to service to repository flow
* Spring bean wiring
* Validation and serialization
* Transactions
* Security filters
* Database queries
* Configuration properties

Unit tests answer: "Does this class behave correctly?"

Integration tests answer: "Does this feature work when real application parts are connected?"

---

# 2. Main Spring Boot Test Annotations

| Annotation | Use |
| ---------- | --- |
| `@SpringBootTest` | Loads full application context |
| `@WebMvcTest` | Tests MVC controller layer only |
| `@DataJpaTest` | Tests JPA repositories |
| `@MockBean` | Replaces a Spring bean with a mock |
| `@AutoConfigureMockMvc` | Adds `MockMvc` for HTTP-style tests |

Use the smallest test slice that proves the behavior.

---

# 3. Full Context Test

```java
@SpringBootTest
@AutoConfigureMockMvc
class OrderApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldCreateOrder() throws Exception {
        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"productId":"p1","quantity":2}
                    """))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.productId").value("p1"));

        assertEquals(1, orderRepository.count());
    }
}
```

This tests web layer, serialization, validation, service wiring, and persistence together.

---

# 4. Controller Slice Test

```java
@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void shouldReturnOrder() throws Exception {
        when(orderService.getOrder("o1"))
            .thenReturn(new OrderResponse("o1", "PAID"));

        mockMvc.perform(get("/orders/o1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("PAID"));
    }
}
```

`@WebMvcTest` is faster than `@SpringBootTest` because it loads only MVC-related beans.

---

# 5. Repository Slice Test

```java
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository repository;

    @Test
    void shouldFindOrdersByCustomerId() {
        entityManager.persist(new OrderEntity("c1", "PAID"));
        entityManager.flush();

        List<OrderEntity> orders = repository.findByCustomerId("c1");

        assertEquals(1, orders.size());
    }
}
```

Use repository tests for custom queries, entity mappings, indexes, and transaction behavior.

---

# 6. Internal Working

Spring Boot tests create an `ApplicationContext`.

The context contains:

* Beans
* Configuration properties
* Auto-configuration
* Web infrastructure if requested
* Database infrastructure if requested

Context startup is expensive. Spring caches contexts between tests when configuration is identical.

Changing profiles, properties, mocks, or test annotations may create a new context and slow the test suite.

---

# 7. Test Profiles and Properties

```java
@SpringBootTest
@ActiveProfiles("test")
class BillingIntegrationTest {
}
```

`application-test.yml`:

```yaml
payment:
  provider: fake
```

Use test profiles for safe configuration. Never let tests accidentally call production services.

---

# 8. Common Mistakes

* Using `@SpringBootTest` for every test.
* Depending on test execution order.
* Leaving database state dirty between tests.
* Mocking too much in an integration test.
* Calling real external services from tests.
* Not checking validation and error responses.
* Ignoring security behavior in controller tests.
* Using H2 when production uses PostgreSQL-specific SQL.

---

# 9. Best Practices

* Use unit tests for business rules and integration tests for wiring and infrastructure.
* Prefer `@WebMvcTest` for controller behavior.
* Prefer `@DataJpaTest` for repository behavior.
* Use Testcontainers for production-like databases.
* Keep integration tests deterministic.
* Clean database state with transactions, SQL cleanup, or container reset strategy.
* Test important failure scenarios, not only `200 OK`.
* Use `MockMvc` for servlet applications and `WebTestClient` for reactive applications.

---

# 10. Real-world Scenarios

* Verify REST endpoint validation returns `400`.
* Verify JSON request maps correctly to DTO.
* Verify JPA query works with real schema.
* Verify transaction rollback on failure.
* Verify security blocks unauthenticated requests.
* Verify application starts with expected configuration.

---

# Revision Notes

* Integration tests verify multiple application parts together.
* `@SpringBootTest` loads full context.
* `@WebMvcTest` focuses on MVC controller layer.
* `@DataJpaTest` focuses on JPA repositories.
* `@MockBean` replaces a Spring bean in the context.
* Spring caches application contexts to reduce test time.
* Use Testcontainers when database compatibility matters.

---

# Cheat Sheet

| Need | Annotation/Tool |
| ---- | --------------- |
| Full app test | `@SpringBootTest` |
| MVC only | `@WebMvcTest` |
| JPA only | `@DataJpaTest` |
| HTTP assertions | `MockMvc` |
| Replace Spring bean | `@MockBean` |
| Test config | `@ActiveProfiles("test")` |
| Real DB container | Testcontainers |

---

# Interview Questions with Answers

### 1. What is the difference between unit and integration testing?

Unit testing checks one class in isolation. Integration testing checks multiple components working together.

### 2. Why avoid `@SpringBootTest` everywhere?

It loads the full context, which is slower and often unnecessary for focused tests.

### 3. What does `@WebMvcTest` load?

It loads MVC components such as controllers, filters, converters, and related web configuration, not the full application.

### 4. Why can H2 be risky for repository tests?

H2 can behave differently from PostgreSQL or MySQL, especially for SQL syntax, types, indexes, and locking.

---

# Hands-on Exercises

### Exercise 1

Write a `MockMvc` test that expects `400 Bad Request` for an invalid request body.

### Exercise 2

Create a repository test for a custom finder method using `@DataJpaTest`.

