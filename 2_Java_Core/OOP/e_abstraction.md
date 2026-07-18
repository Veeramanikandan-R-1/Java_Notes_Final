# OOP in Java

### Subtopic: Abstraction

---

# 1. Fundamentals

Abstraction means hiding implementation details and exposing only essential behavior.

```java
interface PaymentGateway {
    void pay(double amount);
}
```

Caller knows what can be done, not how it is done.

---

# 2. Core Concepts

## 2.1 Interface-based Abstraction

Interfaces define contracts.

```java
interface EmailService {
    void sendEmail(String to, String subject, String body);
}
```

Implementation:

```java
class SmtpEmailService implements EmailService {
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending using SMTP");
    }
}
```

Business code depends on `EmailService`, not `SmtpEmailService`.

---

## 2.2 Abstract Classes

Abstract classes can define common state and partial behavior.

```java
abstract class ReportGenerator {
    void generate() {
        fetchData();
        render();
    }

    abstract void fetchData();
    abstract void render();
}
```

Use abstract classes when subclasses share state or common workflow.

---

## 2.3 Abstract Method

An abstract method has no body.

```java
abstract void process();
```

Subclasses must implement it unless they are abstract too.

---

## 2.4 Interface vs Abstract Class

| Feature | Interface | Abstract Class |
| ------- | --------- | -------------- |
| Purpose | Contract | Shared base behavior |
| State | Constants only normally | Instance fields allowed |
| Multiple inheritance | Multiple interfaces | One abstract class |
| Constructor | No object construction | Constructor allowed |

---

# 3. Internal Working

Abstraction reduces coupling.

```java
class PaymentService {
    private final PaymentGateway gateway;

    PaymentService(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    void checkout(double amount) {
        gateway.pay(amount);
    }
}
```

`PaymentService` can work with Razorpay, Stripe, mock gateway, or any future implementation.

---

# 4. Common Mistakes

* Creating interfaces for every class without variation.
* Putting too many methods in one interface.
* Exposing implementation details through abstraction.
* Using abstract classes when interfaces are enough.
* Making abstraction too generic and hard to understand.

---

# 5. Best Practices

* Abstract behavior that varies.
* Keep interfaces small and focused.
* Depend on abstractions in service code.
* Use abstract classes for shared workflow or state.
* Name abstractions by business capability.
* Avoid premature abstraction.

---

# 6. Code Example

```java
interface InvoiceExporter {
    byte[] export(long invoiceId);
}

class PdfInvoiceExporter implements InvoiceExporter {
    public byte[] export(long invoiceId) {
        return ("PDF invoice " + invoiceId).getBytes();
    }
}

class InvoiceService {
    private final InvoiceExporter exporter;

    InvoiceService(InvoiceExporter exporter) {
        this.exporter = exporter;
    }

    byte[] downloadInvoice(long invoiceId) {
        return exporter.export(invoiceId);
    }
}
```

---

# 7. Real-world Scenarios

* Payment gateway integrations.
* Email/SMS notification providers.
* Storage providers like local, S3, Azure Blob.
* Repository abstractions.
* Test doubles and mocks.

---

# Revision Notes

* Abstraction hides implementation details.
* Interfaces define contracts.
* Abstract classes can hold common state and behavior.
* Depend on abstractions where implementations vary.
* Keep abstractions small and meaningful.
* Avoid premature abstraction.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Contract | Interface |
| Shared workflow | Abstract class |
| Multiple capabilities | Multiple interfaces |
| Hide provider details | Abstraction |
| Testing with mocks | Interface |

---

# Interview Questions with Answers

### 1. What is abstraction?

Showing essential behavior while hiding implementation details.

### 2. Interface vs abstract class?

Use interface for contracts. Use abstract class for shared state or partial implementation.

### 3. Why is abstraction useful in backend systems?

It reduces coupling and allows implementation changes without changing business code.

---

# Hands-on Exercises

### Exercise 1

Create abstraction for file storage.

**Answer**

```java
interface FileStorage {
    void upload(String fileName, byte[] content);
}

class LocalFileStorage implements FileStorage {
    public void upload(String fileName, byte[] content) {
        System.out.println("Saving locally: " + fileName);
    }
}
```

