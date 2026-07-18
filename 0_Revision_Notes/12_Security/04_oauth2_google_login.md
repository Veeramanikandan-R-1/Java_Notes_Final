# Revision Notes

* Google login uses OAuth2 Authorization Code flow with OpenID Connect.
* OIDC provides identity through an ID token.
* Configure Google under `spring.security.oauth2.client.registration.google`.
* Store client secret outside source control.
* Use provider plus subject as stable identity.
* Request minimal scopes: `openid`, `email`, `profile`.
* Google authentication does not replace local authorization.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Enable Google login | `.oauth2Login(...)` |
| Principal type | `OidcUser` |
| Stable identity | `getSubject()` |
| Email | `getEmail()` |
| Scopes | `openid`, `email`, `profile` |
| Secrets | Environment variables |

---

# Interview Q&A

### 1. OAuth2 vs OIDC?

OAuth2 handles delegated access. OIDC adds identity and login.

### 2. Why avoid email-only linking?

Email can change and may be unsafe as the only identity key.

### 3. What is redirect URI?

The callback URL used by Google after authorization.

---

# Exercises

### Exercise 1

Configure Google OAuth2 login with env vars.

### Exercise 2

Create or link a local user by Google subject.

