# Revision Notes

* Adapter makes incompatible interfaces work together.
* Target is the interface expected by application.
* Adaptee is existing or external class.
* Adapter translates between target and adaptee.
* Useful around legacy systems and third-party SDKs.
* Keep adapter focused on translation.

---

# Cheat Sheet

| Part | Meaning |
| ---- | ------- |
| Target | Expected interface |
| Adaptee | Existing incompatible API |
| Adapter | Translator |
| Client | Uses target |

---

# Interview Questions with Answers

### 1. What does Adapter solve?

It allows incompatible APIs to work with existing client code.

### 2. Adapter vs Decorator?

Adapter changes interface. Decorator adds behavior while keeping interface.

### 3. Where use Adapter?

Payment SDKs, email providers, storage APIs, and legacy systems.

