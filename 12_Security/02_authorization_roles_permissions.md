# Authorization

### Subtopic: Roles and Permissions

---

# 1. Fundamentals

Authorization answers: "What is this authenticated user allowed to do?"

Authentication comes first. Authorization uses the authenticated user's roles, permissions, ownership, tenant, account state, and request context to decide access.

In backend systems, authorization protects:

* API endpoints
* Service methods
* Database rows
* Admin operations
* Tenant data
* Sensitive fields

---

# 2. Roles vs Permissions

| Type | Meaning | Example |
| ---- | ------- | ------- |
| Role | Group of access rights | `ADMIN`, `CUSTOMER`, `MANAGER` |
| Permission | Specific action | `ORDER_READ`, `ORDER_CANCEL`, `USER_DELETE` |

Roles are coarse-grained.

Permissions are fine-grained.

Production systems often use both:

```text
ROLE_ADMIN -> USER_READ, USER_DELETE, ORDER_REFUND
ROLE_SUPPORT -> USER_READ, ORDER_READ
ROLE_CUSTOMER -> ORDER_CREATE, ORDER_READ_OWN
```

---

# 3. HTTP Authorization

```java
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/orders/**").hasAuthority("ORDER_READ")
            .requestMatchers(HttpMethod.POST, "/orders").hasAuthority("ORDER_CREATE")
            .anyRequest().authenticated()
        )
        .build();
}
```

`hasRole("ADMIN")` checks for authority `ROLE_ADMIN`.

`hasAuthority("ORDER_READ")` checks the exact authority.

---

# 4. Method Security

Enable method-level authorization:

```java
@Configuration
@EnableMethodSecurity
class MethodSecurityConfig {
}
```

Use it in services:

```java
@Service
class RefundService {

    @PreAuthorize("hasAuthority('ORDER_REFUND')")
    void refund(String orderId) {
        // refund logic
    }
}
```

Method security is useful when the same service can be called from multiple controllers or message consumers.

---

# 5. Ownership Checks

Many rules depend on the current user owning a resource.

```java
@PreAuthorize("@orderSecurity.canView(authentication, #orderId)")
public OrderResponse getOrder(String orderId) {
    return orderRepository.findResponseById(orderId);
}
```

```java
@Component
class OrderSecurity {

    private final OrderRepository orderRepository;

    OrderSecurity(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    boolean canView(Authentication authentication, String orderId) {
        String userId = authentication.getName();
        return orderRepository.existsByIdAndCustomerId(orderId, userId);
    }
}
```

This prevents users from reading other users' orders.

---

# 6. Internal Working

Spring Security stores authorities inside the authenticated `Authentication`.

For endpoint security:

1. Request is authenticated.
2. Authorization filter checks configured matchers.
3. Required authorities are compared with the user's authorities.
4. Access is allowed or denied.

For method security:

1. Spring creates a proxy around secured beans.
2. Method call goes through the proxy.
3. Security expression is evaluated before method execution.
4. Method runs only if the expression allows access.

Self-invocation can bypass method security because internal method calls may not go through the proxy.

---

# 7. Common Mistakes

* Confusing `hasRole("ADMIN")` with `hasAuthority("ADMIN")`.
* Putting all users under one broad role.
* Checking authorization only in the frontend.
* Forgetting ownership and tenant checks.
* Returning `404` vs `403` inconsistently without a policy.
* Trusting request body user IDs.
* Allowing users to change their own role from an API field.
* Assuming URL security protects service methods called from non-HTTP flows.

---

# 8. Best Practices

* Use roles for broad groups and permissions for specific actions.
* Enforce authorization on the backend.
* Keep admin permissions explicit.
* Use method security for business operations.
* Check tenant and ownership close to the data access path.
* Never trust user ID or role from client input.
* Test both allowed and denied paths.
* Use least privilege by default.
* Log denied sensitive actions for audit.

---

# 9. Production Backend Example

```java
@RestController
@RequestMapping("/admin/users")
class AdminUserController {

    private final AdminUserService service;

    AdminUserController(AdminUserService service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    void deleteUser(@PathVariable String id) {
        service.deleteUser(id);
    }
}
```

Security rule:

```java
.requestMatchers("/admin/**").hasRole("ADMIN")
```

The URL check ensures only admins enter admin routes. The method check ensures only admins with `USER_DELETE` can delete users.

---

# 10. Real-world Scenarios

* Customers can view only their own orders.
* Support users can read but not refund orders.
* Managers can approve refunds up to a limit.
* Admins can disable users but cannot read password hashes.
* Tenant admins can manage users only inside their tenant.

---

# Revision Notes

* Authorization decides what an authenticated user can do.
* Roles are coarse-grained groups.
* Permissions are fine-grained actions.
* `hasRole("ADMIN")` checks `ROLE_ADMIN`.
* `hasAuthority("X")` checks exact authority `X`.
* Method security uses proxies and expressions.
* Ownership and tenant checks are required in real systems.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Enable method security | `@EnableMethodSecurity` |
| Role check | `hasRole("ADMIN")` |
| Permission check | `hasAuthority("ORDER_READ")` |
| Method rule | `@PreAuthorize(...)` |
| Current user | `Authentication` |
| Ownership helper | Custom security bean |

---

# Interview Questions with Answers

### 1. Authentication vs authorization?

Authentication verifies identity. Authorization decides allowed actions.

### 2. What is the difference between role and authority?

In Spring Security, both are authorities internally. A role is usually represented with the `ROLE_` prefix.

### 3. Why is frontend-only authorization unsafe?

Attackers can call backend APIs directly, bypassing frontend checks.

### 4. What is object-level authorization?

It checks whether the user can access a specific resource instance, such as their own order.

---

# Hands-on Exercises

### Exercise 1

Protect `/admin/**` so only users with role `ADMIN` can access it.

### Exercise 2

Create an ownership check that allows users to read only their own invoices.

