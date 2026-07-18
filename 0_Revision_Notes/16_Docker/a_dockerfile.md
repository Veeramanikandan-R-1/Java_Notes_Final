# Revision Notes

* Dockerfile is a recipe for building an image.
* Image is immutable; container is a running image instance.
* Dockerfile instructions create cached layers.
* Multi-stage builds keep build tools out of the final image.
* Use `.dockerignore` to reduce build context and avoid accidental files.
* Do not bake secrets into images.
* Production containers should run as non-root.
* Avoid mutable `latest` tags in deployments.

---

# Cheat Sheet

| Need | Command |
| ---- | ------- |
| Build | `docker build -t app:1.0.0 .` |
| Run | `docker run --rm -p 8080:8080 app:1.0.0` |
| Logs | `docker logs <container>` |
| Inspect | `docker inspect <container>` |
| Exec shell | `docker exec -it <container> sh` |

---

# Interview Questions with Answers

### 1. Why use multi-stage builds?

To produce smaller, safer runtime images by separating build dependencies from runtime dependencies.

### 2. Why is layer order important?

Docker cache reuse depends on previous layers staying unchanged.

### 3. What should a Java container log to?

stdout and stderr, so the runtime platform can collect logs.

---

# Hands-on Exercises

### Exercise 1

Build and run an image on port 8080.

**Answer**

```bash
docker build -t orders-api:1.0.0 .
docker run --rm -p 8080:8080 orders-api:1.0.0
```

