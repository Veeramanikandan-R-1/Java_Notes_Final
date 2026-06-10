# Git for Senior Java Backend Engineers

**Perspective: Principal Java Backend Engineer**

Git is not just a source control tool. In modern backend engineering, Git is:

* The foundation of CI/CD
* The audit trail of production systems
* The collaboration layer for distributed teams
* The deployment trigger for cloud-native applications
* The recovery mechanism during outages

Senior engineers are expected to understand not only Git commands but also Git internals, branching strategies, repository health, security, and production workflows.

---

# 1. Fundamentals

## What is Git?

Git is a **distributed version control system (DVCS)**.

Every developer has:

* Full repository
* Entire commit history
* Branches
* Tags

Unlike SVN:

```text
SVN
Client -> Central Server

Git
Developer A <-> Developer B
       \       /
      Remote Repository
```

---

## Core Concepts

### Repository

Contains:

```text
.git/
src/
pom.xml
README.md
```

Git stores history in `.git`.

---

### Commit

Snapshot of project state.

```bash
git commit -m "Add payment service"
```

Think:

```java
ProjectState v1
ProjectState v2
ProjectState v3
```

Each commit points to a snapshot.

---

### Branch

Pointer to a commit.

```bash
git branch feature/payment
```

Branches are lightweight.

---

### Merge

Combines histories.

```bash
git merge feature/payment
```

---

### Rebase

Rewrites history.

```bash
git rebase main
```

Creates cleaner history.

---

### Remote

Shared repository.

```bash
git remote add origin <url>
```

---

# 2. Internal Working

Most developers use Git for years without understanding how it works.

Senior engineers should.

---

## Git Object Model

Git stores four objects:

### Blob

File content.

```java
public class User {}
```

Stored as blob.

---

### Tree

Directory structure.

```text
src/
 └── User.java
```

Stored as tree.

---

### Commit

Metadata.

```text
Author
Date
Message
Parent Commit
Tree Reference
```

---

### Tag

Named reference.

```bash
git tag v1.0
```

---

## Commit Graph

```text
A -> B -> C -> D
```

Each commit contains hash of parent.

Example:

```text
Commit D
Parent = C

Commit C
Parent = B
```

Forms DAG.

**Directed Acyclic Graph**

---

## SHA Hashes

Git identifies objects using SHA.

Example:

```text
8f14e45fceea167a5a36dedd4bea2543
```

Modern Git increasingly supports SHA-256.

---

## HEAD

Current checked-out commit.

```bash
HEAD -> main
```

or

```bash
HEAD -> commit
```

(detached head)

---

## Three Areas

### Working Directory

Actual files.

```java
UserService.java
```

---

### Staging Area

Index.

```bash
git add UserService.java
```

---

### Repository

Commit history.

```bash
git commit
```

Flow:

```text
Working Directory
       ↓
Staging Area
       ↓
Repository
```

---

# 3. Production Usage

Large organizations use Git differently than tutorials.

---

## GitFlow

Branches:

```text
main
develop
feature/*
release/*
hotfix/*
```

Good for:

* Enterprise software
* Long release cycles

---

## Trunk-Based Development

Google-style.

```text
main
 ├── small feature
 ├── small feature
 └── small feature
```

Advantages:

* Faster releases
* Less merge conflicts

Common in:

* Netflix
* Google
* Uber

---

## Production Hotfix

```text
main
 └── hotfix/payment-failure
```

Deploy immediately.

Merge back afterward.

---

## Release Tags

```bash
git tag v2.1.0
```

Deployment pipelines often deploy tags.

---

## Rollbacks

```bash
git revert commitHash
```

Preferred over:

```bash
git reset --hard
```

because history remains intact.

---

# 4. Performance Considerations

Important for large repositories.

---

## Problem: Huge Repository

Example:

```text
500k files
20 GB history
```

Operations become slow.

---

## Shallow Clone

```bash
git clone --depth=1
```

Downloads only latest history.

Useful in CI.

---

## Sparse Checkout

Checkout only needed folders.

```bash
git sparse-checkout init
git sparse-checkout set service-a
```

Monorepos use this heavily.

---

## Partial Clone

```bash
git clone --filter=blob:none
```

Fetch file contents lazily.

Huge performance gain.

---

## Avoid Giant Binary Files

Bad:

```text
video.mp4
dump.zip
```

Git performs poorly.

Use:

```text
S3
Artifactory
Git LFS
```

---

## Git GC

Garbage collection.

```bash
git gc
```

Compresses objects.

Removes unreachable objects.

---

# 5. Memory Considerations

Git stores objects efficiently.

---

## Delta Compression

Instead of storing:

```text
File v1 = 10 MB
File v2 = 10 MB
File v3 = 10 MB
```

Git stores:

```text
v1
delta(v2-v1)
delta(v3-v2)
```

Huge memory savings.

---

## Packfiles

Git compresses objects.

```bash
.git/objects
```

becomes

```text
pack-xxx.pack
```

Much smaller.

---

## Memory Problems

Large:

```text
git log
git blame
git merge
```

may consume significant RAM in huge repos.

Common in monorepos.

---

# 6. Security Considerations

Very important for senior engineers.

---

## Never Commit Secrets

Bad:

```yaml
aws:
  secret: abc123
```

---

Use:

```bash
.env
application-local.yml
vault
secrets manager
```

---

## Add .gitignore

```gitignore
.env
*.pem
*.key
```

---

## Secret Scanning

Tools:

```text
GitGuardian
TruffleHog
Gitleaks
GitHub Secret Scanning
```

---

## Signed Commits

```bash
git commit -S
```

Verifies author identity.

---

## Branch Protection

Prevent:

```text
Force Push
Direct Main Commit
```

on production branches.

---

## If Secret Leaks

Do NOT only delete file.

History still contains it.

Need:

```bash
git filter-repo
```

or

```bash
BFG Repo Cleaner
```

Then rotate credentials.

---

# 7. Spring Boot Usage

Git is deeply integrated with Spring Boot development.

---

## Feature Branch Example

```text
main
feature/user-service
feature/payment-api
```

Each branch modifies:

```java
@RestController
@Service
@Repository
```

independently.

---

## Managing Configuration

Do NOT commit:

```yaml
application-prod.yml
```

with secrets.

Instead:

```yaml
application.yml
application-dev.yml
application-prod.yml
```

Secrets externalized.

---

## Git + CI/CD

Push:

```bash
git push origin main
```

Triggers:

```text
GitHub Actions
Jenkins
GitLab CI
Azure DevOps
```

Pipeline:

```text
Compile
Test
Docker Build
Deploy
```

---

## Versioning

Generate version from Git tag.

Example:

```text
v2.4.0
```

Spring Boot actuator:

```text
/info
```

shows deployed version.

---

# 8. Database Impact

Git does not store database state directly.

But DB migrations depend on Git.

---

## Flyway

```text
V1__create_user.sql
V2__add_email.sql
V3__add_status.sql
```

Tracked in Git.

---

## Merge Conflict Example

Developer A:

```sql
V4__payment.sql
```

Developer B:

```sql
V4__orders.sql
```

Conflict.

Need:

```sql
V5
V6
```

proper sequencing.

---

## Rollbacks

Git rollback != DB rollback

Common mistake.

Need:

```sql
undo scripts
```

or

```sql
forward fix
```

---

# 9. Common Mistakes

## Force Push to Main

```bash
git push --force
```

Can destroy history.

---

## Huge Commits

Bad:

```text
500 files
10000 lines
```

Review nightmare.

---

## Commit Generated Files

Bad:

```text
target/
.idea/
node_modules/
```

---

## Rebase Shared Branch

Dangerous.

```bash
git rebase
git push --force
```

Others suffer.

---

## Commit Secrets

Very common production incident.

---

## Long-Lived Branches

```text
feature branch alive for 4 months
```

Merge conflicts explode.

---

# 10. Best Practices

## Small Commits

Good:

```text
Add payment validation
```

instead of

```text
Fix stuff
```

---

## Commit Frequently

```bash
git commit
```

small logical units.

---

## Rebase Before PR

```bash
git fetch origin
git rebase origin/main
```

Cleaner history.

---

## Protect Main

Require:

```text
PR Review
CI Pass
Security Scan
```

---

## Use Conventional Commits

```text
feat:
fix:
refactor:
test:
docs:
```

Example:

```text
feat(payment): add UPI support
```

---

## Tag Releases

```bash
git tag v3.2.1
```

---

# 11. Senior-Level Interview Questions

### Q1. Difference between merge and rebase?

Merge:

```text
A-B-C
     \
      D-E
```

Creates merge commit.

Rebase:

```text
A-B-C-D-E
```

Linear history.

---

### Q2. What happens during git commit?

Git:

1. Creates tree
2. Creates commit object
3. Stores metadata
4. Updates branch pointer

---

### Q3. Explain HEAD.

Pointer to current commit/branch.

---

### Q4. Difference between reset, revert, restore?

```bash
git reset
```

Move branch pointer.

```bash
git revert
```

Create undo commit.

```bash
git restore
```

Restore files.

---

### Q5. Why is Git fast?

* Content-addressable storage
* Delta compression
* Packfiles
* Local history

---

### Q6. What is detached HEAD?

HEAD points directly to commit.

```bash
git checkout abc123
```

---

### Q7. How does Git detect renames?

Heuristic similarity algorithm.

No explicit rename object.

---

### Q8. What is cherry-pick?

Apply specific commit.

```bash
git cherry-pick commitHash
```

---

### Q9. Explain reflog.

Recovery mechanism.

```bash
git reflog
```

Can recover lost commits.

---

### Q10. Difference between fetch and pull?

```bash
git fetch
```

Download only.

```bash
git pull
```

Fetch + merge/rebase.

---

# 12. Code Examples

## Feature Branch Workflow

```bash
git checkout -b feature/payment
```

---

Develop:

```java
@Service
public class PaymentService {

    public void process() {
        System.out.println("Payment processed");
    }
}
```

---

Commit:

```bash
git add .
git commit -m "feat(payment): add payment service"
```

---

Push:

```bash
git push origin feature/payment
```

---

## Interactive Rebase

Before PR:

```bash
git rebase -i HEAD~3
```

Options:

```text
pick
reword
squash
drop
```

Example:

```text
pick A
squash B
squash C
```

One clean commit.

---

## Recover Lost Commit

```bash
git reflog
```

Find hash:

```bash
abc123
```

Recover:

```bash
git checkout abc123
```

or

```bash
git branch recovery abc123
```

---

# 13. Real-World Scenarios

---

## Scenario 1: Production Bug

Payment service failing.

Current release:

```text
v2.1.0
```

Find commit:

```bash
git bisect
```

Binary search through commits.

Much faster than manual debugging.

---

## Scenario 2: Accidental Deletion

Developer force pushed.

Recover:

```bash
git reflog
```

Restore lost commit.

---

## Scenario 3: Secret Exposed

AWS key committed.

Steps:

1. Rotate key
2. Remove history

```bash
git filter-repo
```

3. Force push
4. Notify security

---

## Scenario 4: Monorepo

Repository:

```text
payments/
orders/
inventory/
shipping/
```

Use:

```bash
sparse checkout
partial clone
```

to improve developer productivity.

---

## Scenario 5: Release Management

```text
main
release/3.1
hotfix/3.1.1
```

Tag:

```bash
git tag v3.1.1
```

Deploy exact version.

---

# Revision Notes

### Must Remember

* Git stores snapshots, not diffs
* Branch = pointer to commit
* Commit = tree + metadata + parent
* HEAD = current location
* Rebase rewrites history
* Merge preserves history
* Revert for production rollback
* Reflog recovers lost commits
* Git uses DAG (Directed Acyclic Graph) internally
* Packfiles and delta compression improve performance
---

# Git Cheat Sheet

### Daily Commands

```bash
git status
git add .
git commit -m "message"
git push
git pull
```

### Branching

```bash
git branch
git checkout -b feature/x
git switch feature/x
```

### History

```bash
git log --oneline --graph
git blame file.java
git reflog
```

### Recovery

```bash
git restore file.java
git revert commit
git reset --hard commit
```

### Advanced

```bash
git rebase -i
git cherry-pick hash
git bisect start
git gc
```

---

# Flashcards

### Q

What does HEAD point to?

### A

Current checked-out branch or commit.

---

### Q

Difference between merge and rebase?

### A

Merge preserves history, rebase rewrites it.

---

### Q

What command recovers lost commits?

### A

```bash
git reflog
```

---

### Q

What is Git's storage model?

### A

Blob, Tree, Commit, Tag objects.

---

### Q

Safest production rollback?

### A

```bash
git revert
```

---

# Active Recall Questions

1. Explain Git internals without using documentation.
2. How does Git store commits?
3. Why is rebase dangerous on shared branches?
4. How would you recover a deleted commit?
5. How does Git compress repository data?
6. How would you remove secrets from history?
7. What branching strategy would you choose for microservices?
8. How would Git integrate with Flyway migrations?
9. How would you debug a production bug using git bisect?
10. How would you manage a 20 GB monorepo?

---

# Hands-on Exercises

### Exercise 1

Create:

```text
main
feature/payment
feature/order
```

Perform:

```bash
merge
rebase
cherry-pick
```

Observe commit graph.

---

### Exercise 2

Accidentally delete a branch.

Recover using:

```bash
git reflog
```

---

### Exercise 3

Create merge conflicts in:

```java
PaymentService.java
```

Resolve manually.

---

### Exercise 4

Run:

```bash
git bisect
```

to identify a bug-introducing commit.

---

### Exercise 5 (Senior Level)

Build a Spring Boot project with:

* GitFlow
* Flyway migrations
* GitHub Actions CI/CD
* Release tags
* Conventional commits
* Protected main branch

This mirrors what many senior backend teams use in production and is excellent preparation for Senior Java Backend Engineer interviews.
