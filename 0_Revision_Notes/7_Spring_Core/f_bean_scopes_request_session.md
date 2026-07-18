# Revision Notes

* Web-aware scopes include request and session.
* Request scope creates one bean per HTTP request.
* Session scope creates one bean per HTTP session.
* These scopes need web application context.
* Avoid storing large or sensitive state in session beans.
* Be careful injecting request-scoped bean into singleton.

---

# Cheat Sheet

| Scope | Meaning |
| ----- | ------- |
| Request | One bean per HTTP request |
| Session | One bean per HTTP session |
| Annotation | `@RequestScope`, `@SessionScope` |
| Risk | State/memory growth |

---

# Interview Questions with Answers

### 1. What is request scope?

A new bean instance is created for each HTTP request.

### 2. What is session scope?

A bean instance is tied to a user's HTTP session.

### 3. Why be careful with session scope?

It can increase memory usage and accidentally store sensitive state.

