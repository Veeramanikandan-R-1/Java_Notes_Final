# Docker

### Subtopic: Dockerfile

---

# 1. Fundamentals

A Dockerfile is a build recipe for creating a container image.

For Java backend services, the goal is to package the application with a predictable runtime so it behaves the same on a developer laptop, CI runner, and server.

Key terms:

| Term | Meaning |
| ---- | ------- |
| Image | Immutable package containing app, runtime, and metadata |
| Container | Running instance of an image |
| Layer | Cached filesystem change created by Dockerfile instructions |
| Build context | Files sent to Docker during image build |
| Registry | Remote image store such as Docker Hub, ECR, or GHCR |

---

# 2. Core Concepts

## Basic Dockerfile

```dockerfile
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/orders-api.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

`FROM` chooses the base runtime. `WORKDIR` sets the working directory. `COPY` adds the application artifact. `EXPOSE` documents the port. `ENTRYPOINT` defines the process.

---

## Build and Run

```bash
docker build -t orders-api:1.0.0 .
docker run --rm -p 8080:8080 orders-api:1.0.0
```

The image should contain only what is needed to run the app. Do not copy source code, local IDE files, or secrets into the runtime image.

---

## Multi-stage Build

```dockerfile
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar
USER 10001
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Multi-stage builds keep Maven and source code out of the final image.

---

# 3. Internal Working

Docker executes instructions top to bottom and creates cached layers. If an earlier layer changes, later layers are rebuilt.

Place stable files first:

```dockerfile
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
```

This improves build speed because dependency downloads are reused until `pom.xml` changes.

Java containers should also account for memory limits. Modern JVMs understand container limits, but production services still need explicit thinking around heap, metaspace, thread count, and startup flags.

---

# 4. Common Mistakes

* Building huge images with SDKs and source code in the final runtime.
* Running containers as root.
* Using `latest` tags in production deployments.
* Copying `.env`, credentials, or local config into the image.
* Ignoring `.dockerignore`.
* Putting frequently changing files before dependency layers.
* Treating containers as virtual machines and running multiple unrelated processes.

---

# 5. Best Practices

* Use small trusted base images.
* Prefer multi-stage builds.
* Pin image versions for production.
* Add a `.dockerignore`.
* Run with a non-root user.
* Keep configuration external through environment variables or mounted config.
* Log to stdout/stderr.
* Build once and promote the same image across environments.

---

# 6. Code Example

```dockerfile
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar
RUN adduser --disabled-password --gecos "" appuser
USER appuser
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75"
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
```

---

# 7. Real-world Scenarios

* Packaging Spring Boot REST APIs for Kubernetes or ECS.
* Creating consistent local environments for onboarding.
* Building immutable artifacts in CI.
* Debugging "works on my machine" runtime differences.
* Promoting the same tested image to staging and production.

---

# Revision Notes

* Dockerfile builds a container image.
* Container runs one main process.
* Layers are cached in instruction order.
* Multi-stage builds reduce final image size.
* `.dockerignore` protects build context and speed.
* Production images should avoid root and `latest`.

---

# Cheat Sheet

| Need | Command |
| ---- | ------- |
| Build image | `docker build -t app:1.0.0 .` |
| Run container | `docker run --rm -p 8080:8080 app:1.0.0` |
| List images | `docker images` |
| List containers | `docker ps` |
| View logs | `docker logs <container>` |
| Shell into container | `docker exec -it <container> sh` |

---

# Interview Questions with Answers

### 1. Why use a multi-stage Dockerfile for Java?

It separates build tooling from runtime so the final image is smaller, cleaner, and safer.

### 2. Why should production not use `latest`?

`latest` is mutable. Deployments become non-repeatable because the same tag can point to a different image later.

### 3. Why run as non-root?

If the application is compromised, a non-root user reduces the damage possible inside the container and host integration points.

---

# Hands-on Exercises

### Exercise 1

Write a command to build and run a Spring Boot image on port 8080.

**Answer**

```bash
docker build -t orders-api:1.0.0 .
docker run --rm -p 8080:8080 orders-api:1.0.0
```

