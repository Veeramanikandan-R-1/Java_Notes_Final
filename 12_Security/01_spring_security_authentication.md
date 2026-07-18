# Spring Security

### Subtopic: Authentication

---

# 1. Fundamentals

Authentication answers: "Who is this user?"

Spring Security is the standard security framework for Spring applications. It protects HTTP endpoints, authenticates users, manages sessions, stores security context, and integrates with form login, basic auth, JWT, OAuth2, LDAP, and custom identity providers.

In backend systems, authentication usually involves:

* Receiving credentials or a token
* Validating identity
* Creating an authenticated principal
* Storing authentication for the current request
* Rejecting unauthenticated access

---

# 2. Core Concepts

| Concept | Meaning |
| ------- | ------- |
| Principal | The authenticated user identity |
| Credentials | Secret used to prove identity |
| Authentication | Object representing authentication state |
| SecurityContext | Stores authentication for current execution |
| Filter Chain | Servlet filters that apply security checks |
| UserDetailsService | Loads user details by username |
| PasswordEncoder | Hashes and verifies passwords |

---

# 3. Security Filter Chain

Modern Spring Security configuration uses `SecurityFilterChain`.

```java
@Configuration
class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .build();
    }
}
```

This configuration allows public login/register routes and requires authentication for everything else.

---

# 4. Internal Working

For a secured request:

1. Request enters Spring Security filter chain.
2. Authentication filter extracts credentials.
3. `AuthenticationManager` asks an `AuthenticationProvider` to authenticate.
4. Provider loads user details and checks credentials.
5. If valid, an authenticated `Authentication` object is created.
6. Authentication is stored in `SecurityContext`.
7. Authorization checks decide whether the request can access the endpoint.
8. Controller executes only if authentication and authorization pass.

---

# 5. UserDetailsService

```java
@Service
class DatabaseUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    DatabaseUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        return org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            .password(user.getPasswordHash())
            .roles(user.getRole())
            .disabled(!user.isEnabled())
            .build();
    }
}
```

`UserDetailsService` loads user data. It should not compare raw passwords itself.

---

# 6. Password Hashing

Never store plain text passwords.

```java
@Bean
PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

Registration example:

```java
String hash = passwordEncoder.encode(request.password());
userRepository.save(new UserEntity(request.email(), hash));
```

Login verification is handled by Spring Security using `PasswordEncoder.matches(...)`.

---

# 7. Session Authentication

Traditional login stores authentication in an HTTP session.

Best for:

* Server-rendered apps
* Internal admin panels
* Browser apps using same backend domain

Less ideal for:

* Mobile APIs
* Stateless microservices
* Cross-domain public APIs

For APIs, JWT is often used instead of server-side sessions.

---

# 8. Common Mistakes

* Storing plain text passwords.
* Disabling CSRF without understanding the client type.
* Returning too much user information after login.
* Using email existence error messages that allow account enumeration.
* Writing custom crypto.
* Forgetting account states such as disabled, locked, expired, or unverified.
* Logging passwords, tokens, or authorization headers.
* Assuming authentication means authorization.

---

# 9. Best Practices

* Use BCrypt, Argon2, or another approved password encoder.
* Keep login errors generic.
* Rate limit login attempts.
* Use HTTPS only.
* Keep authentication and authorization separate.
* Do not expose password hashes in APIs.
* Audit successful and failed login attempts.
* Use secure cookie settings for session-based authentication.
* Use multi-factor authentication for sensitive systems.

---

# 10. Production Backend Example

```java
@RestController
class MeController {

    @GetMapping("/me")
    UserProfile currentUser(Authentication authentication) {
        return new UserProfile(authentication.getName());
    }
}
```

`Authentication` can be injected into controller methods after the request is authenticated.

---

# 11. Real-world Scenarios

* Login with email and password.
* Protect all account endpoints.
* Return current user profile from `/me`.
* Block disabled accounts.
* Force password reset after suspicious activity.
* Audit login failures for security monitoring.

---

# Revision Notes

* Authentication verifies user identity.
* Spring Security runs through a servlet filter chain.
* `SecurityContext` stores current authentication.
* `UserDetailsService` loads user data.
* `PasswordEncoder` hashes and verifies passwords.
* Store password hashes, never plain text passwords.
* Authentication does not automatically mean permission to access everything.

---

# Cheat Sheet

| Need | Spring Security |
| ---- | --------------- |
| Configure HTTP security | `SecurityFilterChain` |
| Require login | `.authenticated()` |
| Public route | `.permitAll()` |
| Load user | `UserDetailsService` |
| Hash password | `PasswordEncoder` |
| Current user | `Authentication` |
| Password hash | `BCryptPasswordEncoder` |

---

# Interview Questions with Answers

### 1. What is authentication?

Authentication is the process of verifying who the user is.

### 2. What is `SecurityContext`?

It stores the current request's authenticated principal and authorities.

### 3. Why should passwords be hashed?

If the database leaks, hashes reduce the chance that attackers can recover original passwords.

### 4. What is `UserDetailsService`?

It loads user information by username for Spring Security authentication.

---

# Hands-on Exercises

### Exercise 1

Create a `PasswordEncoder` bean using BCrypt.

### Exercise 2

Create a `/me` endpoint that returns the authenticated user's username.

