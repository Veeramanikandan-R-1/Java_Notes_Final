# Capstone: Secure User Service

### Subtopic: Spring Security + JWT

---

# 1. Goal

Build a secure user service with authentication, authorization, and JWT token-based access.

This project validates:

* Spring Security
* Password hashing
* JWT generation/validation
* Role-based access
* Global exception handling
* Secure API design

---

# 2. Core Features

* Register user.
* Login user.
* Generate JWT.
* Validate JWT on protected APIs.
* Role-based authorization.
* Get current user profile.
* Refresh token optional.

---

# 3. Data Model

Tables:

* users
* roles
* user_roles

User fields:

```text
id, email, password_hash, enabled, created_at
```

Never store plain password.

---

# 4. Security Flow

```text
Login request -> authenticate credentials -> generate JWT -> client sends Bearer token -> filter validates token -> SecurityContext set
```

---

# 5. API Design

```text
POST /api/auth/register
POST /api/auth/login
GET /api/users/me
GET /api/admin/users
```

Admin API should require admin role.

---

# 6. Best Practices

* Hash passwords with BCrypt.
* Use short-lived access tokens.
* Keep JWT secret secure.
* Do not log tokens.
* Validate issuer/expiry.
* Use HTTPS.
* Return safe error messages.

---

# Revision Notes

* Spring Security handles authentication and authorization.
* JWT carries claims and expiry.
* Passwords must be hashed.
* Security filter validates token.
* Roles protect endpoints.
* Do not log secrets.
* Use HTTPS in production.

---

# Interview Questions with Answers

### 1. Why hash password?

So stolen database does not expose actual passwords.

### 2. What is JWT?

A signed token containing claims used for stateless authentication.

### 3. Where is authenticated user stored?

In Spring Security `SecurityContext` for the current request.

