# Revision Notes

* Volumes persist state outside containers.
* Networks isolate and connect containers.
* Environment variables inject runtime configuration.
* Secrets should come from secret managers or platform secret features.
* Container writable layers are disposable.
* Publish only ports required by users or external systems.
* Never store production credentials in Dockerfiles, images, or source.

---

# Cheat Sheet

| Need | Command/Concept |
| ---- | --------------- |
| Create volume | `docker volume create orders_db` |
| Mount volume | `-v orders_db:/var/lib/postgresql/data` |
| Create network | `docker network create backend` |
| Set env | `-e SPRING_PROFILES_ACTIVE=prod` |
| Inspect config | `docker inspect <container>` |

---

# Interview Questions with Answers

### 1. What is a named volume?

A Docker-managed storage location that can be attached to containers and survive container deletion.

### 2. Why avoid secrets in environment variables when possible?

They can be exposed through process or container inspection, logs, and crash dumps.

### 3. What is the production approach for persistent state?

Prefer managed databases, object storage, or durable platform volumes instead of relying on container writable layers.

---

# Hands-on Exercises

### Exercise 1

Create a network and run Redis on it.

**Answer**

```bash
docker network create backend
docker run -d --name redis --network backend redis:7
```

