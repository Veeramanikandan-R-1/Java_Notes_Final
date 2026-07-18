# OAuth2

### Subtopic: Google Login

---

# 1. Fundamentals

OAuth2 login lets users sign in through an external identity provider such as Google.

In Spring Security, Google login usually uses OAuth2 Authorization Code flow with OpenID Connect.

OAuth2 answers delegated access.

OpenID Connect adds identity on top of OAuth2.

For login, the application uses Google to verify the user's identity, then creates a local authenticated session or token.

---

# 2. Key Concepts

| Concept | Meaning |
| ------- | ------- |
| Client ID | Public application identifier from Google |
| Client Secret | Secret used by backend confidential clients |
| Redirect URI | Callback URL after Google login |
| Authorization Code | Short-lived code returned by Google |
| ID Token | OIDC token containing user identity claims |
| Access Token | Token used to call Google APIs |
| Scope | Requested access, such as `openid`, `email`, `profile` |

---

# 3. Flow

1. User clicks "Login with Google".
2. App redirects user to Google authorization endpoint.
3. User authenticates and consents.
4. Google redirects back with authorization code.
5. Backend exchanges code for tokens.
6. Spring validates ID token.
7. Spring creates authenticated principal.
8. App creates local user record if needed.
9. User is logged in.

---

# 4. Spring Security Configuration

```java
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/login").permitAll()
            .anyRequest().authenticated()
        )
        .oauth2Login(Customizer.withDefaults())
        .build();
}
```

`application.yml`:

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: ${GOOGLE_CLIENT_ID}
            client-secret: ${GOOGLE_CLIENT_SECRET}
            scope:
              - openid
              - email
              - profile
```

Never commit real client secrets.

---

# 5. Reading the Logged-in User

```java
@RestController
class ProfileController {

    @GetMapping("/profile")
    Map<String, Object> profile(@AuthenticationPrincipal OidcUser user) {
        return Map.of(
            "email", user.getEmail(),
            "name", user.getFullName(),
            "subject", user.getSubject()
        );
    }
}
```

Use Google's stable subject claim for identity linking. Email can change.

---

# 6. Internal Working

Spring Security OAuth2 login uses multiple filters and services:

* Authorization request repository stores login request state.
* Redirect filter sends user to Google.
* Login authentication filter handles callback.
* Client registration contains provider metadata.
* Authorized client service stores tokens if needed.
* OIDC user service loads user information.

Spring validates important OIDC details such as token issuer and signature based on provider metadata.

---

# 7. Local User Linking

Most production apps create or link a local user.

```java
@Service
class GoogleUserProvisioningService {

    private final UserRepository userRepository;

    GoogleUserProvisioningService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    UserEntity findOrCreate(OidcUser oidcUser) {
        return userRepository.findByProviderAndProviderSubject("google", oidcUser.getSubject())
            .orElseGet(() -> userRepository.save(
                new UserEntity("google", oidcUser.getSubject(), oidcUser.getEmail())
            ));
    }
}
```

Do not link accounts only by email unless your security model accepts that risk.

---

# 8. Common Mistakes

* Committing Google client secret.
* Using email as permanent identity instead of provider subject.
* Misconfiguring redirect URI.
* Requesting unnecessary scopes.
* Confusing Google access token with application session.
* Not handling first-time user provisioning.
* Assuming every Google account belongs to your organization.
* Not validating hosted domain when building organization-only login.

---

# 9. Best Practices

* Store secrets in environment variables or a secret manager.
* Use `openid`, `email`, and `profile` only unless Google API access is needed.
* Link users by provider and provider subject.
* Restrict hosted domains for organization-only apps.
* Keep local authorization separate from Google authentication.
* Define behavior for disabled local users.
* Use HTTPS redirect URIs in production.
* Log security-relevant login events without logging tokens.

---

# 10. Production Backend Scenarios

* Internal admin panel uses Google Workspace login.
* SaaS app allows Google login but still has local roles.
* User signs up with Google and receives `CUSTOMER` role.
* Existing email/password user links Google as another login method.
* Backend exchanges OAuth2 login session for an application JWT.

---

# Revision Notes

* Google login uses OAuth2 Authorization Code flow with OpenID Connect.
* OIDC provides identity through ID token.
* `client-id`, `client-secret`, and redirect URI must match Google configuration.
* Use provider subject for stable identity.
* OAuth2 login authenticates the user; local authorization still belongs to the app.
* Store secrets outside source control.
* Request minimal scopes.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Enable login | `.oauth2Login(...)` |
| User principal | `OidcUser` |
| Stable Google identity | `oidcUser.getSubject()` |
| Email | `oidcUser.getEmail()` |
| Required scopes | `openid`, `email`, `profile` |
| Config path | `spring.security.oauth2.client.registration.google` |
| Secret storage | Environment variables |

---

# Interview Questions with Answers

### 1. OAuth2 vs OpenID Connect?

OAuth2 is for delegated authorization. OpenID Connect adds identity and login on top of OAuth2.

### 2. Why not use email as the only identity key?

Email can change and may not be globally safe for account linking. Provider plus subject is more stable.

### 3. What is a redirect URI?

The callback URL where Google sends the user after authorization.

### 4. Does Google login replace application authorization?

No. Google authenticates identity. The application still decides roles and permissions.

---

# Hands-on Exercises

### Exercise 1

Configure Google OAuth2 login using environment variables for client ID and secret.

### Exercise 2

Create a service that maps Google `sub` to a local user record.

