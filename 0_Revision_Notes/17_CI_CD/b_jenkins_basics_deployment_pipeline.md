# Revision Notes

* Jenkins automates build and deployment workflows.
* `Jenkinsfile` defines pipeline as code.
* Controller schedules work; agents execute steps.
* Stages make deployment progress visible.
* Use credentials binding for secrets.
* Add manual approval for risky environments.
* Smoke tests verify deployment success.
* Build once and promote the same artifact or image.

---

# Cheat Sheet

| Need | Jenkins |
| ---- | ------- |
| Pipeline file | `Jenkinsfile` |
| Stage block | `stage('Build')` |
| Shell command | `sh 'mvn -B clean verify'` |
| Approval | `input message: 'Deploy?'` |
| Secret binding | `withCredentials` |

---

# Interview Questions with Answers

### 1. What is a Jenkins agent?

A worker machine that executes pipeline steps scheduled by the Jenkins controller.

### 2. Why avoid hardcoded credentials?

They can leak through source control, logs, and copied pipeline files.

### 3. What is a deployment smoke test?

A small post-deploy check that confirms the service is reachable and basic functionality works.

---

# Hands-on Exercises

### Exercise 1

Write a Jenkins stage that builds a Maven project.

**Answer**

```groovy
stage('Build') {
  steps {
    sh 'mvn -B clean verify'
  }
}
```

