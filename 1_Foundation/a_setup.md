## Environment Setup — Java Spring Boot

For Spring Boot development, your basic environment should be:

**JDK 21 + IntelliJ IDEA/VS Code + Git + GitHub + Maven**

### 1. JDK 21

**JDK (Java Development Kit)** is required to develop and run Java applications.

It contains:

* **JVM** → Runs Java bytecode.
* **JRE-related runtime components** → Libraries/runtime needed to execute Java.
* **Compiler (`javac`)** → Converts `.java` → `.class`.
* Development tools such as `javadoc`, `jdb`, etc.

For your learning, use **JDK 21 (LTS)**.

Verify installation:

```bash
java -version
javac -version
```

Example:

```text
java version "21.x.x"
```

Important interview point:

**JDK → JRE → JVM**

```text
JDK
 └── Runtime
      └── JVM
```

Also configure `JAVA_HOME` to point to your JDK installation.

---

### 2. IntelliJ IDEA

IntelliJ IDEA is the preferred IDE for Java/Spring Boot development.

Useful features:

* Code completion
* Debugging
* Maven/Gradle integration
* Git integration
* Spring Boot support
* Refactoring
* Unit testing
* Run configurations

For learning Spring Boot, **IntelliJ IDEA is my recommended choice**.

When creating a project, make sure the project SDK is:

```text
JDK 21
```

---

### 3. VS Code

Visual Studio Code can also be used for Java/Spring Boot.

Install extensions such as:

* Extension Pack for Java
* Spring Boot Extension Pack
* Maven for Java

**IntelliJ vs VS Code**

|                | IntelliJ      | VS Code          |
| -------------- | ------------- | ---------------- |
| Java support   | ⭐⭐⭐⭐⭐         | ⭐⭐⭐⭐             |
| Spring Boot    | Excellent     | Good             |
| Refactoring    | Excellent     | Good             |
| Lightweight    | No            | Yes              |
| Recommendation | **Preferred** | Good alternative |

You don't need both. **Start with IntelliJ.**

---

### 4. Git

Git is used for version control.

Basic workflow:

```text
Modify code
   ↓
git add
   ↓
git commit
   ↓
git push
   ↓
GitHub
```

Important commands:

```bash
git init
git clone <repository-url>

git status
git add .
git commit -m "Initial commit"

git pull
git push

git branch
git checkout -b feature/login
git merge
```

For interviews, understand the difference between:

* `git pull` → fetch + merge/rebase
* `git fetch` → downloads changes but doesn't merge
* `git merge` → combines branches
* `git rebase` → reapplies commits on top of another branch
* `git revert` → creates a new commit that undoes a previous commit
* `git reset` → moves HEAD and can modify commit history

---

### 5. GitHub

GitHub is a cloud platform for hosting Git repositories.

Typical workflow:

```text
Local machine
     ↓
    Git
     ↓
  GitHub
     ↓
Pull Request
     ↓
Code Review
     ↓
Merge
```

Example:

```bash
git clone https://github.com/user/spring-demo.git

cd spring-demo

git checkout -b feature/user-api

git add .
git commit -m "Add user API"

git push -u origin feature/user-api
```

Then create a **Pull Request (PR)** on GitHub.

### 6. Recommended setup for your Spring Boot journey

Install/configure in this order:

```text
1. JDK 21
      ↓
2. IntelliJ IDEA
      ↓
3. Git
      ↓
4. GitHub account
      ↓
5. Maven
      ↓
6. Spring Boot project
```

**Important:** You don't necessarily need to install Maven separately if you use the **Maven Wrapper (`mvnw`)** generated with your Spring Boot project.

### Interview must-know

Remember these:

* **JDK** → Develop Java applications.
* **JVM** → Executes Java bytecode.
* **JDK 21** → LTS version suitable for your learning.
* **IntelliJ** → IDE for Java/Spring development.
* **Git** → Version control.
* **GitHub** → Remote repository/code collaboration platform.
* **Maven** → Build and dependency management tool.
* **`pom.xml`** → Maven project's configuration/dependencies file.
* **`JAVA_HOME`** → Points to the installed JDK.
