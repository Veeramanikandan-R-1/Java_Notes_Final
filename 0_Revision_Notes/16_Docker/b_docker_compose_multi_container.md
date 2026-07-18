# Revision Notes

* Docker Compose runs related containers together.
* A service can reach another service by service name.
* `localhost` inside a container means that same container.
* `ports` publish a container port to the host.
* Named volumes preserve data across container recreation.
* `depends_on` alone does not guarantee readiness.
* Health checks help, but apps still need retry logic.

---

# Cheat Sheet

| Need | Command |
| ---- | ------- |
| Start | `docker compose up --build` |
| Start detached | `docker compose up -d` |
| Logs | `docker compose logs -f api` |
| Stop | `docker compose down` |
| Stop and remove volumes | `docker compose down -v` |
| Exec | `docker compose exec api sh` |

---

# Interview Questions with Answers

### 1. Why use Compose for backend development?

It starts the API and dependencies such as database, cache, and broker with one configuration.

### 2. How does service discovery work?

Compose creates a network where service names resolve through Docker DNS.

### 3. When should you avoid exposing ports?

When a service is only needed internally by other containers.

---

# Hands-on Exercises

### Exercise 1

Connect an API service to Redis in Compose.

**Answer**

```yaml
environment:
  SPRING_DATA_REDIS_HOST: redis
```

