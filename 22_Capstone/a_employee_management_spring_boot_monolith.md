# Capstone: Employee Management

### Subtopic: Spring Boot Monolith

---

# 1. Goal

Build a Spring Boot monolith for managing employees, departments, roles, and basic admin workflows.

This project validates:

* REST APIs
* DTOs
* Validation
* Exception handling
* JPA
* PostgreSQL
* Unit testing

---

# 2. Core Features

* Create employee.
* Update employee.
* Get employee by ID.
* List employees with pagination.
* Filter by department/status.
* Assign department.
* Soft delete employee.

---

# 3. Suggested Architecture

```text
controller -> service -> repository -> database
       DTO <-> mapper <-> entity
```

Packages:

```text
com.example.employee
  controller
  service
  repository
  entity
  dto
  exception
  mapper
```

---

# 4. Data Model

Tables:

* employees
* departments
* roles

Employee fields:

```text
id, first_name, last_name, email, department_id, status, created_at, updated_at
```

---

# 5. API Design

```text
POST /api/employees
GET /api/employees/{id}
GET /api/employees?page=0&size=20
PUT /api/employees/{id}
DELETE /api/employees/{id}
```

Use request/response DTOs, not entities.

---

# 6. Best Practices

* Use constructor injection.
* Validate request DTOs.
* Use global exception handler.
* Add pagination.
* Keep service transactional.
* Add indexes on email and department.
* Write unit tests for service layer.

---

# 7. Deliverables

* Working REST API.
* PostgreSQL schema.
* Validation errors.
* Global exception response.
* Unit tests.
* README with run steps.

---

# Revision Notes

* Monolith is good first capstone.
* Keep controller, service, repository layers clear.
* Use DTOs for APIs.
* Use JPA entities for persistence.
* Use validation and global exception handling.
* Add pagination and filtering.
* Test service logic.

---

# Interview Questions with Answers

### 1. Why use DTOs?

To decouple API contract from database entity model.

### 2. Where to put transaction?

Usually service layer because it owns business use case.

### 3. Why soft delete?

To preserve historical records while hiding deleted employees from normal queries.

