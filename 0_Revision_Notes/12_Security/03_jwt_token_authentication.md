# Revision Notes

* JWT is a signed token format.
* JWT structure is `header.payload.signature`.
* Payload is not encrypted by default.
* Access tokens are sent as bearer tokens.
* Validate signature, expiry, issuer, and audience.
* JWT is useful for stateless APIs.
* Keep access tokens short-lived and claims minimal.

---

# Cheat Sheet

| Need | JWT |
| ---- | --- |
| User ID | `sub` |
| Expiry | `exp` |
| Issuer | `iss` |
| Audience | `aud` |
| Header | `Authorization: Bearer <token>` |
| Stateless sessions | `SessionCreationPolicy.STATELESS` |
| Spring support | OAuth2 Resource Server |

---

# Interview Q&A

### 1. Is JWT encrypted?

Usually no. A signed JWT protects integrity but does not hide claims.

### 2. Why short token expiry?

It limits damage if the token is stolen.

### 3. How is logout handled?

With short expiry, refresh token revocation, or a token blocklist when needed.

---

# Exercises

### Exercise 1

Configure JWT resource server authentication.

### Exercise 2

Map a `roles` claim to Spring authorities.

