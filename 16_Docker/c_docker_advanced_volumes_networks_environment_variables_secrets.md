# Docker Advanced

### Subtopic: Volumes, Networks, Environment Variables, Secrets

---

# 1. Fundamentals

Advanced Docker usage focuses on how containers interact with data, networks, configuration, and sensitive values.

Containers are disposable. Data and configuration should be managed outside the image so the same image can run in different environments.

---

# 2. Core Concepts

## Volumes

Volumes persist data beyond a container lifecycle.

```bash
docker volume create orders_db
docker run -v orders_db:/var/lib/postgresql/data postgres:16
```

Use volumes for database data, broker state, uploaded files in local development, and other stateful dependencies.

---

## Networks

Docker networks isolate and connect containers.

```bash
docker network create backend
docker run --network backend --name api orders-api:1.0.0
docker run --network backend --name redis redis:7
```

Containers on the same user-defined bridge network can resolve each other by container name.

---

## Environment Variables

Environment variables inject runtime configuration.

```bash
docker run -e SPRING_PROFILES_ACTIVE=prod orders-api:1.0.0
```

They are good for non-secret configuration such as profiles, URLs, feature flags, and tuning values.

---

## Secrets

Secrets are sensitive values such as passwords, tokens, and private keys.

Do not bake secrets into images. Prefer platform secret stores such as AWS Secrets Manager, Kubernetes Secrets with encryption, Docker secrets, GitHub Actions secrets, or Vault.

---

# 3. Internal Working

A container filesystem is ephemeral. When the container is removed, writable layer changes disappear unless stored in a volume or external service.

Environment variables are visible to the process and often visible through inspection tools, so they should not be treated as a perfect secret boundary.

Networks create isolation boundaries. Only publish ports that must be accessed from outside the Docker host.

---

# 4. Common Mistakes

* Storing production secrets in Dockerfiles or image layers.
* Using bind mounts in production when named volumes or managed storage are safer.
* Publishing database ports publicly.
* Confusing container environment variables with build arguments.
* Keeping important state only inside a disposable container layer.
* Reusing the same credentials across local, staging, and production.

---

# 5. Best Practices

* Treat images as immutable and environment-neutral.
* Use volumes for stateful local dependencies.
* Use managed databases and object storage for production state when possible.
* Keep secrets in a secret manager.
* Use least-privilege networks and port exposure.
* Separate build-time values from runtime values.
* Rotate credentials and avoid long-lived static secrets.

---

# 6. Code Example

```yaml
services:
  api:
    image: orders-api:1.0.0
    environment:
      SPRING_PROFILES_ACTIVE: prod
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/orders
    secrets:
      - db_password
    networks:
      - backend

  db:
    image: postgres:16
    environment:
      POSTGRES_DB: orders
      POSTGRES_USER: orders_user
      POSTGRES_PASSWORD_FILE: /run/secrets/db_password
    volumes:
      - orders_db:/var/lib/postgresql/data
    networks:
      - backend
    secrets:
      - db_password

volumes:
  orders_db:

networks:
  backend:

secrets:
  db_password:
    file: ./secrets/db_password.txt
```

---

# 7. Real-world Scenarios

* Keeping PostgreSQL data after local container restarts.
* Isolating backend-only services from public host ports.
* Deploying the same image with different environment-specific config.
* Moving secrets from `.env` files to AWS Secrets Manager.
* Debugging data loss caused by container removal without a volume.

---

# Revision Notes

* Volumes persist data.
* Networks control container connectivity.
* Environment variables provide runtime config.
* Secrets must not be baked into images.
* Container writable layers are disposable.
* Only publish required ports.

---

# Cheat Sheet

| Need | Command/Concept |
| ---- | --------------- |
| Create volume | `docker volume create name` |
| Mount volume | `-v name:/path` |
| Create network | `docker network create backend` |
| Use network | `--network backend` |
| Set env | `-e KEY=value` |
| Inspect | `docker inspect <container>` |

---

# Interview Questions with Answers

### 1. What problem do Docker volumes solve?

They persist data outside the container writable layer so data survives container recreation.

### 2. Why should secrets not be stored in Docker images?

Image layers are reusable and inspectable. Once a secret is in a layer, removing it in a later layer does not reliably erase exposure.

### 3. What is the difference between `ARG` and `ENV`?

`ARG` is mainly for build-time values. `ENV` is available to the running container.

---

# Hands-on Exercises

### Exercise 1

Run PostgreSQL with a named volume.

**Answer**

```bash
docker volume create orders_db
docker run --name orders-db -e POSTGRES_PASSWORD=local -v orders_db:/var/lib/postgresql/data postgres:16
```

