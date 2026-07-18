# Revision Notes

* Authorization decides what an authenticated user can do.
* Roles are broad groups; permissions are specific actions.
* `hasRole("ADMIN")` checks for `ROLE_ADMIN`.
* `hasAuthority("ORDER_READ")` checks exact authority.
* `@EnableMethodSecurity` enables method-level checks.
* `@PreAuthorize` protects service or controller methods.
* Ownership and tenant checks are essential in production.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Role check | `hasRole("ADMIN")` |
| Permission check | `hasAuthority("X")` |
| Method security | `@PreAuthorize` |
| Enable method security | `@EnableMethodSecurity` |
| Current user | `Authentication` |
| Object access | Custom security bean |

---

# Interview Q&A

### 1. Authentication vs authorization?

Authentication verifies identity. Authorization checks access rights.

### 2. Why backend authorization?

Frontend checks can be bypassed by direct API calls.

### 3. What is object-level authorization?

Checking access to a specific resource, such as an order owned by the current user.

---

# Exercises

### Exercise 1

Restrict `/admin/**` to role `ADMIN`.

### Exercise 2

Add `@PreAuthorize` for `ORDER_REFUND`.

