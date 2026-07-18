# Revision Notes

* Authentication verifies who the user is.
* Spring Security applies authentication through a filter chain.
* `SecurityContext` stores the current authentication.
* `UserDetailsService` loads user data.
* `PasswordEncoder` hashes and verifies passwords.
* Store password hashes, never plain text passwords.
* Authentication is separate from authorization.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| HTTP security | `SecurityFilterChain` |
| Public route | `permitAll()` |
| Protected route | `authenticated()` |
| Load user | `UserDetailsService` |
| Hash password | `BCryptPasswordEncoder` |
| Current user | `Authentication` |

---

# Interview Q&A

### 1. What is authentication?

Verifying the identity of a user or client.

### 2. What is `SecurityContext`?

The holder for the current authenticated principal and authorities.

### 3. Why use BCrypt?

It is a slow password hashing algorithm designed to make brute force attacks harder.

---

# Exercises

### Exercise 1

Create a BCrypt `PasswordEncoder` bean.

### Exercise 2

Protect all endpoints except `/login` and `/register`.

