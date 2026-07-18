# Revision Notes

* DTO means Data Transfer Object.
* Request DTO models input.
* Response DTO models output.
* Do not expose JPA entities directly.
* DTOs protect API contract from internal model changes.
* DTOs help validation and security.
* Mapping can be manual or via mapper library.

---

# Cheat Sheet

| Type | Purpose |
| ---- | ------- |
| Request DTO | API input |
| Response DTO | API output |
| Entity | DB persistence |
| Mapper | Convert DTO/entity |
| Validation | On request DTO |

---

# Interview Questions with Answers

### 1. Why use DTOs?

To decouple API contract from persistence model.

### 2. Why not return entities?

It can expose internal fields, cause lazy loading issues, and tightly couple API to DB model.

### 3. Where validate input?

On request DTO using Bean Validation.

