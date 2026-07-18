# Docker Compose

### Subtopic: Multi Container

---

# 1. Fundamentals

Docker Compose runs multiple related containers from one YAML file.

For Java backend development, it is commonly used to start the API, database, cache, message broker, and observability tools together.

Compose is mainly a local and lower-environment orchestration tool. Production orchestration is usually handled by Kubernetes, ECS, Nomad, or similar platforms.

---

# 2. Core Concepts

## Compose File

```yaml
services:
  api:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/orders
    depends_on:
      - db

  db:
    image: postgres:16
    environment:
      POSTGRES_DB: orders
      POSTGRES_USER: orders_user
      POSTGRES_PASSWORD: orders_pass
    ports:
      - "5432:5432"
```

Each service gets a DNS name equal to the service name. The API can reach PostgreSQL using host `db`, not `localhost`.

---

## Useful Commands

```bash
docker compose up --build
docker compose up -d
docker compose logs -f api
docker compose down
docker compose down -v
```

Use `down -v` carefully because it removes named volumes and can delete local database data.

---

## Health Checks

`depends_on` controls startup order but does not guarantee readiness unless health conditions are configured.

```yaml
services:
  db:
    image: postgres:16
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U orders_user -d orders"]
      interval: 10s
      timeout: 5s
      retries: 5
```

Applications should still implement retries because databases and brokers can restart after the app is already running.

---

# 3. Internal Working

Compose creates a project network by default. Services on that network can discover each other by service name.

Published ports expose container ports to the host machine. Internal service-to-service calls use container ports directly.

Example:

```text
Browser -> localhost:8080 -> api container port 8080
api -> db:5432 -> db container port 5432
```

---

# 4. Common Mistakes

* Using `localhost` inside one container to call another container.
* Assuming `depends_on` means dependency is fully ready.
* Committing local passwords meant only for developer machines.
* Mapping every internal service port to the host unnecessarily.
* Forgetting that `docker compose down -v` removes data volumes.
* Letting local Compose drift far away from production topology.

---

# 5. Best Practices

* Use service names for internal communication.
* Add health checks for stateful dependencies.
* Keep local credentials low-risk and environment-specific.
* Use named volumes for database persistence.
* Keep Compose files readable and close to the app.
* Use profiles for optional services such as observability tools.
* Make the default `docker compose up` useful for a new developer.

---

# 6. Code Example

```yaml
services:
  api:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_PROFILES_ACTIVE: local
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/orders
      SPRING_DATASOURCE_USERNAME: orders_user
      SPRING_DATASOURCE_PASSWORD: orders_pass
    depends_on:
      db:
        condition: service_healthy

  db:
    image: postgres:16
    environment:
      POSTGRES_DB: orders
      POSTGRES_USER: orders_user
      POSTGRES_PASSWORD: orders_pass
    volumes:
      - orders_db:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U orders_user -d orders"]
      interval: 10s
      timeout: 5s
      retries: 5

volumes:
  orders_db:
```

---

# 7. Real-world Scenarios

* Running API plus PostgreSQL for local development.
* Starting Redis and Kafka for integration testing.
* Reproducing staging bugs locally with similar dependencies.
* Teaching new team members a single command setup.
* Running contract test dependencies in CI.

---

# Revision Notes

* Compose manages multi-container environments.
* Services communicate through service names.
* `ports` expose containers to the host.
* Named volumes persist data.
* Health checks improve readiness handling.
* `down -v` removes volumes.

---

# Cheat Sheet

| Need | Command |
| ---- | ------- |
| Start foreground | `docker compose up --build` |
| Start detached | `docker compose up -d` |
| Stop | `docker compose down` |
| Remove volumes | `docker compose down -v` |
| Logs | `docker compose logs -f api` |
| Run command | `docker compose exec api sh` |

---

# Interview Questions with Answers

### 1. Why does a container use `db` instead of `localhost`?

`localhost` means the same container. Compose DNS resolves `db` to the database container.

### 2. What is the difference between `ports` and internal networking?

`ports` expose a service to the host. Internal networking lets containers communicate without host exposure.

### 3. Is Compose a replacement for Kubernetes?

Usually no. Compose is excellent for local and simple environments, while Kubernetes provides production scheduling, scaling, rollout, and recovery features.

---

# Hands-on Exercises

### Exercise 1

Add Redis to a Compose file and connect a Java app to it.

**Answer**

```yaml
services:
  redis:
    image: redis:7
  api:
    environment:
      SPRING_DATA_REDIS_HOST: redis
      SPRING_DATA_REDIS_PORT: 6379
```

