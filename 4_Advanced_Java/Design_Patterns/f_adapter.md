# Adapter Design Pattern

### Subtopic: Adapter

---

# 1. Fundamentals

Adapter converts one interface into another expected interface.

Use it when existing code or external library does not match your application contract.

---

# 2. Core Concepts

## Target Interface

```java
interface PaymentGateway {
    void pay(double amount);
}
```

## External Service

```java
class LegacyPaymentClient {
    void makePayment(int amountInPaise) {
        System.out.println("Legacy payment");
    }
}
```

## Adapter

```java
class LegacyPaymentAdapter implements PaymentGateway {
    private final LegacyPaymentClient client;

    LegacyPaymentAdapter(LegacyPaymentClient client) {
        this.client = client;
    }

    public void pay(double amount) {
        client.makePayment((int) (amount * 100));
    }
}
```

---

# 3. Internal Working

Client code depends on target interface.

Adapter translates calls to the adaptee.

This protects business code from external API shape.

---

# 4. Common Mistakes

* Putting business logic inside adapter.
* Leaking external API types into internal code.
* Adapter becoming too large.
* Ignoring error translation.
* Not testing mapping/conversion logic.

---

# 5. Best Practices

* Keep adapter focused on translation.
* Hide external library details.
* Convert external exceptions to internal exceptions.
* Test data mapping carefully.
* Use adapter around third-party integrations.

---

# 6. Real-world Scenarios

* Payment gateway integration.
* External email provider.
* Legacy system wrapper.
* Third-party SDK wrapper.
* Storage provider abstraction.

---

# Revision Notes

* Adapter makes incompatible interfaces work together.
* Target is what application expects.
* Adaptee is existing/external service.
* Adapter translates between them.
* Useful around third-party APIs.

---

# Cheat Sheet

| Part | Meaning |
| ---- | ------- |
| Target | Expected interface |
| Adaptee | Existing incompatible class |
| Adapter | Translator |
| Client | Uses target |

---

# Interview Questions with Answers

### 1. What problem does Adapter solve?

It allows incompatible interfaces to work together.

### 2. Adapter vs Decorator?

Adapter changes interface. Decorator keeps interface and adds behavior.

### 3. Where use Adapter?

Around legacy systems and third-party SDKs.

