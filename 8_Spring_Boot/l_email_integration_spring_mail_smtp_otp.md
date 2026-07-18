# Spring Boot

### Subtopic: Email Integration - Spring Mail, SMTP, OTP

---

# 1. Fundamentals

Spring Boot can send email using Spring Mail and SMTP.

Common dependency:

```text
spring-boot-starter-mail
```

Spring provides `JavaMailSender` to create and send messages.

---

# 2. Core Concepts

## SMTP Configuration

```properties
spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=${SMTP_USERNAME}
spring.mail.password=${SMTP_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

---

## Sending Simple Email

```java
SimpleMailMessage message = new SimpleMailMessage();
message.setTo(email);
message.setSubject("Welcome");
message.setText("Your account is ready");
mailSender.send(message);
```

---

## OTP Flow

Typical OTP flow:

```text
Generate OTP -> store hash with expiry -> send email -> verify user input -> mark used
```

Never log OTP values in production.

---

# 3. Internal Working

`JavaMailSender` connects to SMTP using configured host, port, username, password, and TLS settings.

For OTP, the application should store expiry, attempt count, and used status.

For high volume, email sending should often be async or queue-backed.

---

# 4. Common Mistakes

* Hardcoding SMTP password.
* Logging OTP values.
* Storing OTP as plain text.
* No expiry or attempt limit.
* Sending email inside critical transaction path without fallback.
* Not handling SMTP failures.

---

# 5. Best Practices

* Use environment variables for credentials.
* Store hashed OTP with expiry.
* Rate-limit OTP requests.
* Use async processing or queue for sending.
* Use templates for production email content.
* Track provider failures and retries.

---

# 6. Code Example

```java
@Service
class EmailService {
    private final JavaMailSender mailSender;

    EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    void sendOtp(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Login OTP");
        message.setText("Your OTP expires in 5 minutes.");
        mailSender.send(message);
    }
}
```

---

# 7. Real-world Scenarios

* Registration confirmation.
* Password reset OTP.
* Login verification.
* Invoice emails.
* Alert notifications.

---

# Revision Notes

* Use `spring-boot-starter-mail`.
* `JavaMailSender` sends email.
* SMTP credentials must be externalized.
* OTP should have expiry, attempt limit, and used status.
* Do not log or store OTP in plain text.
* Use async or queues for non-critical email sending.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Mail dependency | `spring-boot-starter-mail` |
| Sender | `JavaMailSender` |
| Simple text mail | `SimpleMailMessage` |
| SMTP host | `spring.mail.host` |
| TLS | `mail.smtp.starttls.enable` |

---

# Interview Questions with Answers

### 1. What is `JavaMailSender`?

Spring's abstraction for sending email through mail infrastructure such as SMTP.

### 2. How should OTP be stored?

As a hash with expiry, attempt count, and used status.

### 3. Why send email asynchronously?

SMTP can be slow or fail, and user-facing flows should not block unnecessarily.

---

# Hands-on Exercises

### Exercise 1

Write SMTP username config using environment variable.

**Answer**

```properties
spring.mail.username=${SMTP_USERNAME}
```

