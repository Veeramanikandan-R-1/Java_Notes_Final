# Jenkins Basics

### Subtopic: Deployment Pipeline

---

# 1. Fundamentals

Jenkins is an automation server used to build, test, package, and deploy applications.

In Java backend teams, Jenkins often coordinates deployments to VMs, Kubernetes, or cloud services using a `Jenkinsfile` stored with the code.

Deployment pipelines should be repeatable, auditable, and able to stop before risky environments.

---

# 2. Core Concepts

## Jenkinsfile

```groovy
pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        sh 'mvn -B clean verify'
      }
    }
  }
}
```

A declarative pipeline defines stages and steps. Keeping the pipeline as code makes deployment behavior reviewable.

---

## Typical Deployment Flow

```text
Checkout -> Build -> Test -> Package -> Build image -> Push image -> Deploy -> Smoke test
```

Manual approval is common before production:

```groovy
input message: 'Deploy to production?'
```

---

## Credentials

Jenkins credentials store secrets and injects them into jobs when needed.

Use credential bindings instead of hardcoding passwords or tokens in the `Jenkinsfile`.

---

# 3. Internal Working

Jenkins controller schedules work. Agents execute pipeline steps.

The workspace contains checked-out code and generated outputs. Builds must not rely on old workspace state because agents can be reused or replaced.

Pipeline status should be based on commands exiting correctly. If a shell command hides failures, Jenkins may mark a broken deployment as successful.

---

# 4. Common Mistakes

* Deploying directly from a developer machine.
* Hardcoding credentials in pipeline files.
* Skipping smoke tests after deployment.
* Rebuilding a different artifact for each environment.
* Not separating build and deploy responsibility.
* Leaving production deploys without approval or rollback plan.
* Allowing long-lived mutable workspaces to hide missing setup.

---

# 5. Best Practices

* Store `Jenkinsfile` in source control.
* Build once and promote the same artifact or image.
* Use credentials binding.
* Add clear stages and timestamps.
* Fail fast on test and quality gate failures.
* Add smoke tests after deployment.
* Keep production deploys auditable.
* Maintain rollback steps or redeploy previous image tags.

---

# 6. Code Example

```groovy
pipeline {
  agent any

  environment {
    IMAGE = "registry.example.com/orders-api:${env.BUILD_NUMBER}"
  }

  stages {
    stage('Build and Test') {
      steps {
        sh 'mvn -B clean verify'
      }
    }

    stage('Build Image') {
      steps {
        sh 'docker build -t $IMAGE .'
      }
    }

    stage('Push Image') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'registry-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
          sh 'echo "$PASS" | docker login registry.example.com -u "$USER" --password-stdin'
          sh 'docker push $IMAGE'
        }
      }
    }

    stage('Deploy') {
      steps {
        input message: 'Deploy to staging?'
        sh './deploy.sh $IMAGE staging'
      }
    }
  }
}
```

---

# 7. Real-world Scenarios

* Deploying a Spring Boot JAR to an EC2 instance.
* Building and pushing a Docker image to a registry.
* Promoting the same image from staging to production.
* Running database migration checks before deployment.
* Adding manual approval for production release.

---

# Revision Notes

* Jenkins automates CI/CD through jobs and pipelines.
* `Jenkinsfile` keeps pipeline definition in code.
* Controllers schedule work; agents run work.
* Credentials must be stored in Jenkins credentials store.
* Deployments need smoke tests and rollback thinking.
* Build once, promote the same artifact.

---

# Cheat Sheet

| Need | Jenkins Concept |
| ---- | --------------- |
| Pipeline code | `Jenkinsfile` |
| Build stages | `stages` |
| Commands | `sh` |
| Manual approval | `input` |
| Secrets | `withCredentials` |
| Build identity | `BUILD_NUMBER` |

---

# Interview Questions with Answers

### 1. Why store Jenkins pipelines as code?

It makes pipeline changes versioned, reviewable, and tied to the application.

### 2. What is build once, deploy many?

The same artifact or image is promoted across environments instead of rebuilding separately for each environment.

### 3. Why add smoke tests after deployment?

They verify the deployed service actually starts and handles basic requests in the target environment.

---

# Hands-on Exercises

### Exercise 1

Add a manual approval step before deployment.

**Answer**

```groovy
input message: 'Deploy to production?'
```

