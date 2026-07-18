# JWT

### Subtopic: Token Authentication

---

# 1. Fundamentals

JWT means JSON Web Token.

JWT authentication is commonly used for stateless APIs. After login, the server issues a signed token. The client sends that token with future requests.

Typical header:

```http
Authorization: Bearer eyJhbGciOi...
```

The backend validates the token and builds authentication for the request.

---

# 2. JWT Structure

A JWT has three parts:

```text
header.payload.signature
```

| Part | Contains |
| ---- | -------- |
| Header | Algorithm and token type |
| Payload | Claims such as subject, roles, expiry |
| Signature | Proof that token was signed by trusted server |

Payload is base64url encoded, not encrypted by default. Do not put secrets in JWT payload.

---

# 3. Common Claims

| Claim | Meaning |
| ----- | ------- |
| `sub` | Subject, usually user ID |
| `iss` | Issuer |
| `aud` | Audience |
| `exp` | Expiration time |
| `iat` | Issued at |
| `jti` | Token ID |

Custom claims may include roles or permissions, but keep tokens small.

---

# 4. Internal Working

For JWT protected APIs:

1. User logs in with credentials.
2. Server validates credentials.
3. Server creates signed access token.
4. Client stores token securely.
5. Client sends token in `Authorization` header.
6. JWT filter extracts bearer token.
7. Server validates signature, expiry, issuer, and audience.
8. Server creates `Authentication` for the request.
9. Authorization rules run using token authorities.

The server does not need a session for every request.

---

# 5. Spring Security JWT Resource Server

For APIs that validate JWTs:

```java
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/login").permitAll()
            .anyRequest().authenticated()
        )
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
        .build();
}
```

This config treats the app as a resource server that accepts bearer JWTs.

---

# 6. Mapping Roles from JWT

```java
@Bean
JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter authoritiesConverter =
        new JwtGrantedAuthoritiesConverter();
    authoritiesConverter.setAuthoritiesClaimName("roles");
    authoritiesConverter.setAuthorityPrefix("ROLE_");

    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
    return converter;
}
```

Then wire it:

```java
.oauth2ResourceServer(oauth2 -> oauth2
    .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
)
```

---

# 7. Access Token and Refresh Token

| Token | Purpose |
| ----- | ------- |
| Access token | Short-lived token used for API calls |
| Refresh token | Longer-lived token used to get new access tokens |

Access tokens should expire quickly.

Refresh tokens need stronger protection, rotation, revocation, and audit.

---

# 8. Common Mistakes

* Putting passwords, secrets, or sensitive PII in JWT payload.
* Using long-lived access tokens.
* Accepting tokens without validating signature.
* Ignoring issuer or audience validation.
* Storing tokens unsafely in browser local storage for high-risk apps.
* Not rotating refresh tokens.
* Not planning logout or revocation.
* Trusting roles from unsigned or client-generated tokens.

---

# 9. Best Practices

* Use HTTPS only.
* Keep access tokens short-lived.
* Validate signature, expiry, issuer, and audience.
* Store signing keys securely.
* Rotate keys using key IDs when possible.
* Do not log tokens.
* Prefer proven libraries and identity providers.
* Use refresh token rotation for public clients.
* Keep token claims minimal.
* Use opaque tokens when server-side revocation is more important than statelessness.

---

# 10. Production Backend Example

```java
@RestController
class AccountController {

    @GetMapping("/accounts/me")
    AccountResponse me(JwtAuthenticationToken authentication) {
        String userId = authentication.getToken().getSubject();
        return new AccountResponse(userId);
    }
}
```

The subject should usually be a stable user ID, not a changeable email address.

---

# 11. Real-world Scenarios

* Mobile app calls backend APIs with bearer token.
* API gateway validates JWT before forwarding requests.
* Microservice validates tokens issued by central identity provider.
* Admin APIs require permissions from token claims.
* Refresh token is rotated after each use.

---

# Revision Notes

* JWT is a signed token format.
* JWT has header, payload, and signature.
* Payload is not encrypted by default.
* APIs usually receive JWT in `Authorization: Bearer`.
* Server validates signature, expiry, issuer, and audience.
* JWT works well for stateless APIs.
* Short-lived access tokens reduce damage if stolen.

---

# Cheat Sheet

| Need | JWT Concept |
| ---- | ----------- |
| User identity | `sub` |
| Expiry | `exp` |
| Issuer | `iss` |
| Audience | `aud` |
| API header | `Authorization: Bearer <token>` |
| Stateless API | `SessionCreationPolicy.STATELESS` |
| Spring validator | OAuth2 Resource Server |

---

# Interview Questions with Answers

### 1. Is JWT encrypted?

Not by default. A normal signed JWT protects integrity, not confidentiality.

### 2. Why use short-lived access tokens?

If stolen, they remain useful for less time.

### 3. What must be validated in JWT authentication?

Signature, expiration, issuer, audience, and any required claims.

### 4. How does logout work with JWT?

Pure stateless JWT logout is hard. Common solutions include short expiry, refresh token revocation, or token blocklists for sensitive cases.

---

# Hands-on Exercises

### Exercise 1

Configure Spring Security as a stateless JWT resource server.

### Exercise 2

Map a `roles` JWT claim to Spring Security authorities.

