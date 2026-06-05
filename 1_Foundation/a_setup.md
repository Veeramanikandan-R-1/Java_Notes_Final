# Environment Setup (JDK 21, IntelliJ IDEA, VS Code, Git, GitHub)

### Senior Java Backend Engineer Learning Series – Day 1

---

# 1. Fundamentals

## Why Environment Setup Matters

Before writing a single line of code, professional engineers need a reliable development environment.

A proper setup enables:

| Benefit              | Why Important               |
| -------------------- | --------------------------- |
| Faster Development   | Better tooling              |
| Better Debugging     | IDE support                 |
| Team Collaboration   | Git/GitHub                  |
| Build Automation     | Maven/Gradle                |
| Production Alignment | Same JDK version everywhere |
| CI/CD Integration    | Git-based workflows         |

Think of your environment as the workshop where all software is built.

---

## Core Components

### JDK (Java Development Kit)

Contains:

```text
JDK
├── JVM
├── JRE
├── Compiler (javac)
├── Debugger
├── Tools
```

### IntelliJ IDEA

Professional Java IDE.

Provides:

* Code completion
* Refactoring
* Debugging
* Maven support
* Spring Boot support

Industry standard for Java development.

---

### VS Code

Lightweight editor.

Useful for:

* Markdown
* YAML
* Docker
* Kubernetes
* Java projects

---

### Git

Version Control System.

Tracks:

```text
Code Changes
↓
Commits
↓
Branches
↓
Merges
```

---

### GitHub

Cloud-hosted Git repository.

Used for:

* Code storage
* Pull requests
* Code reviews
* CI/CD
* Collaboration

---

# 2. Internal Working

---

## How Java Compilation Works

```text
Java Source
(Main.java)
        |
        v
javac
        |
        v
Bytecode
(Main.class)
        |
        v
JVM
        |
        v
Machine Code
```

Example:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
```

Compile:

```bash
javac Main.java
```

Run:

```bash
java Main
```

---

## JDK vs JRE vs JVM

### JVM

Executes bytecode.

Responsible for:

* Memory management
* Garbage collection
* Thread scheduling

---

### JRE

Contains:

```text
JVM
+
Java Libraries
```

Used only to run applications.

---

### JDK

Contains:

```text
JRE
+
Compiler
+
Development Tools
```

Used for development.

---

# 3. Production Usage

Senior engineers maintain consistency.

Example:

```text
Developer Machine
      ↓
GitHub
      ↓
CI/CD Pipeline
      ↓
Docker
      ↓
Kubernetes
      ↓
Production
```

All use:

```text
Java 21
```

Never:

```text
Developer = Java 17
Production = Java 21
```

Version mismatches cause outages.

---

# 4. Performance Considerations

---

## IntelliJ

Allocate sufficient memory.

Recommended:

```text
4GB - 8GB RAM
```

Check:

```text
Help
→ Change Memory Settings
```

---

## JDK Version

Use:

```text
Java 21 LTS
```

Benefits:

* Better GC
* Better performance
* Virtual Threads
* Latest optimizations

---

## SSD Storage

Use SSD.

Reason:

```text
Build Speed
Indexing
Dependency Download
```

are significantly faster.

---

# 5. Memory Considerations

---

## IntelliJ Indexing

IntelliJ builds indexes.

```text
Project Files
      ↓
Indexes
      ↓
Fast Navigation
```

Consumes RAM.

Normal behavior.

---

## JVM Memory

When running apps:

```text
Heap
Stack
Metaspace
Native Memory
```

Senior engineers monitor:

```bash
jcmd
jmap
jvisualvm
```

---

# 6. Security Considerations

---

## Never Commit

```text
passwords
tokens
keys
credentials
```

Bad:

```properties
db.password=mysecret
```

Good:

```properties
db.password=${DB_PASSWORD}
```

---

## Git Ignore

Create:

```gitignore
.idea/
target/
.env
*.log
```

---

## GitHub Secrets

Store:

```text
AWS Keys
Database Passwords
API Tokens
```

in:

```text
GitHub Secrets
```

Never in source code.

---

# 7. Spring Boot Usage

---

Create project:

```bash
spring init \
--dependencies=web,data-jpa \
demo
```

Or use:

```text
https://start.spring.io
```

---

Typical Structure

```text
src
├── main
│   ├── java
│   └── resources
├── test
```

---

Run:

```bash
./mvnw spring-boot:run
```

---

# 8. Database Impact

---

Install:

```text
PostgreSQL
```

Avoid:

```text
MySQL Workbench only
```

Learn PostgreSQL.

Most Spring Boot projects use:

```text
PostgreSQL
```

---

Tools:

```text
DBeaver
pgAdmin
```

Recommended:

```text
DBeaver
```

---

Connection Example

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/demo
spring.datasource.username=postgres
spring.datasource.password=password
```

---

# 9. Common Mistakes

| Mistake                 | Why Bad                 |
| ----------------------- | ----------------------- |
| Using old JDK           | Missing features        |
| Not learning Git        | Team productivity issue |
| Committing secrets      | Security breach         |
| Working on main branch  | Risky                   |
| Ignoring GitHub PRs     | Poor collaboration      |
| Not using Maven Wrapper | Build inconsistency     |
| Installing random JDKs  | Version conflicts       |

---

# 10. Best Practices

### Use

```text
Java 21 LTS
```

---

### Use

```text
IntelliJ IDEA Community/Ultimate
```

---

### Learn

```bash
git
```

before Spring Boot.

---

### Always Create Feature Branches

```bash
git checkout -b feature/user-api
```

---

### Commit Frequently

```bash
git commit -m "Add user validation"
```

---

# 11. Interview Questions

### Q1

What is the difference between JDK, JRE, and JVM?

**Answer**

| Component | Purpose         |
| --------- | --------------- |
| JVM       | Runs bytecode   |
| JRE       | JVM + libraries |
| JDK       | JRE + tools     |

---

### Q2

Why should teams standardize Java versions?

Answer:

Prevent:

* Runtime failures
* Dependency incompatibility
* Production issues

---

### Q3

What is Git?

Answer:

Distributed Version Control System.

Tracks source code changes.

---

### Q4

Why use GitHub Pull Requests?

Answer:

* Code review
* Quality checks
* Team collaboration

---

### Q5

What is Maven Wrapper?

Answer:

Ensures everyone uses the same Maven version.

Files:

```text
mvnw
mvnw.cmd
```

---

# 12. Code Examples

---

## Verify Java

```bash
java --version
```

Expected:

```text
openjdk 21
```

---

## Verify Git

```bash
git --version
```

---

## Create Repository

```bash
git init
```

---

## First Commit

```bash
git add .
git commit -m "Initial commit"
```

---

## Push to GitHub

```bash
git remote add origin URL

git push -u origin main
```

---

# 13. Real-World Scenarios

### E-Commerce

```text
Spring Boot
PostgreSQL
GitHub
Docker
```

---

### Banking

```text
Java 21
Spring Security
GitHub Enterprise
```

---

### Healthcare

```text
GitHub Actions
Static Code Analysis
Secure Repositories
```

---

### SaaS

```text
Microservices
Spring Boot
GitHub CI/CD
```

---

### Social Media

```text
Java
Kafka
Redis
PostgreSQL
```

---

# Revision Notes

### Must Know

| Topic      | Key Point         |
| ---------- | ----------------- |
| JVM        | Executes bytecode |
| JRE        | Runtime           |
| JDK        | Development Kit   |
| Git        | Version Control   |
| GitHub     | Remote Repository |
| IntelliJ   | Primary Java IDE  |
| Java 21    | Current LTS       |
| PostgreSQL | Preferred DB      |

---

# One-Page Cheat Sheet

```text
Install:
- JDK 21
- IntelliJ IDEA
- VS Code
- Git
- PostgreSQL
- DBeaver

Commands:

java --version
javac Main.java
java Main

git init
git add .
git commit -m "message"
git push

Never Commit:
- passwords
- tokens
- keys

Always:
- use feature branches
- use pull requests
- use Java 21
```

---

# Flashcards

### Q1

What runs Java bytecode?

**A:** JVM

### Q2

What contains the compiler?

**A:** JDK

### Q3

What command checks Java version?

**A:**

```bash
java --version
```

### Q4

What tracks source code changes?

**A:** Git

### Q5

What hosts Git repositories?

**A:** GitHub

### Q6

Why use feature branches?

**A:** Isolate development

### Q7

What should never be committed?

**A:** Secrets and credentials

### Q8

Preferred Java version?

**A:** Java 21 LTS

### Q9

Preferred IDE?

**A:** IntelliJ IDEA

### Q10

Preferred DB for Spring Boot?

**A:** PostgreSQL

---

# Active Recall Questions

1. Explain JDK, JRE, and JVM without looking at notes.
2. What happens from `.java` file to machine code?
3. Why should all environments use the same Java version?
4. Why are feature branches important?
5. What belongs in `.gitignore`?
6. Why should secrets never be committed?
7. How does Git differ from GitHub?
8. Why is Java 21 preferred today?
9. What tools would you install on a new machine for Spring Boot development?
10. What would happen if production runs Java 17 but development runs Java 21?

---

# Hands-On Exercises

### Easy

* Install Java 21
* Install Git
* Install IntelliJ
* Verify versions

### Medium

* Create GitHub account
* Create repository
* Push first Java project

### Hard

* Create Spring Boot project
* Push to GitHub
* Configure PostgreSQL
* Connect application to database

### Production-Level Task

Build a repository named:

```text
backend-learning-lab
```

Include:

```text
Java 21
Spring Boot 3
PostgreSQL
Maven Wrapper
GitHub Repository
README.md
.gitignore
```

and push it using a professional Git workflow with feature branches and pull requests.
