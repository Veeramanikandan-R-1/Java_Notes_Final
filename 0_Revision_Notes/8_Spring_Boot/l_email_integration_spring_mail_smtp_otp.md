# Revision Notes

* Spring Mail sends email through SMTP.
* `JavaMailSender` is main API.
* Configure host, port, username, password.
* OTP should expire quickly.
* Do not log OTP or SMTP password.
* Email sending should usually be async.
* Use templates for production email content.

---

# Cheat Sheet

| Need | API/Concept |
| ---- | ----------- |
| Send mail | `JavaMailSender` |
| SMTP config | host/port/user/pass |
| HTML email | `MimeMessage` |
| OTP expiry | TTL |
| Async send | `@Async` |

---

# Interview Questions with Answers

### 1. What is SMTP?

Protocol used to send email.

### 2. Why send email async?

Email provider latency should not slow user request.

### 3. How secure OTP?

Short expiry, one-time use, rate limiting, and no logging.

