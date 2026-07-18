# Revision Notes

* REST controllers expose HTTP APIs.
* `@RestController` returns response body directly.
* `@RequestBody` binds JSON body.
* `@PathVariable` reads URL path value.
* `@RequestParam` reads query parameter.
* `@RequestHeader` reads HTTP header.
* `ResponseEntity` controls body, headers, and status.
* Use correct HTTP status codes.

---

# Cheat Sheet

| Need | Annotation/API |
| ---- | -------------- |
| REST controller | `@RestController` |
| POST mapping | `@PostMapping` |
| Body | `@RequestBody` |
| Path value | `@PathVariable` |
| Query param | `@RequestParam` |
| Header | `@RequestHeader` |
| Status/body | `ResponseEntity` |

---

# Interview Questions with Answers

### 1. @Controller vs @RestController?

`@RestController` combines `@Controller` and `@ResponseBody`.

### 2. When use ResponseEntity?

When you need control over status, headers, and body.

### 3. PathVariable vs RequestParam?

PathVariable is part of URL path. RequestParam is query string value.

