# Git — Must-Know Commands for Spring Boot

As a Spring Boot developer, you should be comfortable with the **daily Git workflow**, branching, merging/rebasing, undoing changes, and resolving conflicts.

## 1. Repository Setup

```bash
git init
```

Creates a new local Git repository.

```bash
git clone <url>
```

Downloads an existing remote repository.

```bash
git remote -v
```

Shows connected remote repositories.

---

## 2. Check Changes

```bash
git status
```

Shows:

* Modified files
* Untracked files
* Staged files
* Current branch

**One of the most frequently used commands.**

```bash
git diff
```

Shows unstaged changes.

```bash
git diff --staged
```

Shows staged changes.

---

## 3. Add & Commit

```bash
git add .
```

Stages all changes.

```bash
git add UserService.java
```

Stages a specific file.

```bash
git commit -m "Add user service"
```

Creates a commit.

Typical workflow:

```text
Modify
  ↓
git add
  ↓
git commit
```

---

## 4. Push & Pull

```bash
git push
```

Uploads local commits to the remote repository.

```bash
git pull
```

Downloads remote changes and integrates them into your current branch.

Conceptually:

```text
git pull = git fetch + git merge
```

You can also use:

```bash
git fetch
```

Downloads remote changes **without modifying your current working branch**.

### Interview difference

**`fetch` vs `pull`:**

* `fetch` → Download changes only.
* `pull` → Download + integrate changes.

---

# 5. Branches

List branches:

```bash
git branch
```

Create branch:

```bash
git branch feature/user-api
```

Switch branch:

```bash
git switch feature/user-api
```

Create + switch:

```bash
git switch -c feature/user-api
```

Delete branch:

```bash
git branch -d feature/user-api
```

Typical Spring Boot workflow:

```text
main
  │
  └── feature/user-api
          │
          ├── code
          ├── commit
          └── push
                 ↓
              Pull Request
```

---

# 6. Merge

```bash
git switch main
git merge feature/user-api
```

Combines the feature branch into `main`.

### Merge conflict

If Git cannot automatically combine changes:

```text
<<<<<<< HEAD
your code
=======
other branch code
>>>>>>> feature/user-api
```

You manually resolve the conflict, then:

```bash
git add .
git commit
```

---

# 7. Rebase ⭐

```bash
git switch feature/user-api
git rebase main
```

Moves your feature commits on top of the latest `main`.

Conceptually:

```text
Before:

main:    A---B---C
              \
feature:       D---E


After rebase:

main:    A---B---C
                  \
feature:           D'---E'
```

### Merge vs Rebase

**Merge:**

* Preserves branch history
* May create merge commit

**Rebase:**

* Creates cleaner/linear history
* Rewrites commit history

⚠️ Avoid rebasing commits that are already shared with others unless you understand the consequences.

---

# 8. View History

```bash
git log
```

Compact:

```bash
git log --oneline
```

Very useful:

```bash
git log --oneline --graph --all
```

Shows branch/commit history visually.

---

# 9. Undo Changes

### Discard unstaged changes

```bash
git restore file.java
```

### Unstage a file

```bash
git restore --staged file.java
```

### Amend latest commit

```bash
git commit --amend
```

Useful when you forgot something in the last commit.

---

# 10. Reset ⭐

```bash
git reset --soft HEAD~1
```

Undo last commit but **keep changes staged**.

```bash
git reset --mixed HEAD~1
```

Undo last commit and **keep changes unstaged**.

```bash
git reset --hard HEAD~1
```

Undo last commit and **delete the changes**.

⚠️ `--hard` can permanently discard work.

---

# 11. Revert ⭐

```bash
git revert <commit-id>
```

Creates a **new commit that reverses an existing commit**.

This is generally safer for commits that are already pushed/shared.

### Reset vs Revert

| Command  | What it does                               |
| -------- | ------------------------------------------ |
| `reset`  | Moves branch/HEAD backward                 |
| `revert` | Creates a new commit undoing an old commit |

**Interview answer:**
For a pushed/shared commit, prefer `git revert` rather than rewriting public history with `git reset`.

---

# 12. Stash

Temporarily save unfinished work:

```bash
git stash
```

View stashes:

```bash
git stash list
```

Restore:

```bash
git stash pop
```

Example:

```text
You're working on feature A
        ↓
Urgent bug on feature B
        ↓
git stash
        ↓
switch to feature B
        ↓
fix bug
        ↓
switch back
        ↓
git stash pop
```

---

# 13. Cherry-pick ⭐

```bash
git cherry-pick <commit-id>
```

Copies a specific commit from another branch into your current branch.

Example:

```text
feature-A
   |
   D ← useful bug fix

feature-B
   |
   E---F

Switch to feature-B
        ↓
git cherry-pick D
```

Now the changes from `D` are applied to `feature-B`.

Useful for selectively bringing a particular fix/commit.

---

# 14. Remote Branches

```bash
git branch -a
```

Shows local + remote branches.

```bash
git fetch origin
```

Updates remote-tracking information.

```bash
git push -u origin feature/user-api
```

Pushes a new branch and sets its upstream branch.

After that, simply:

```bash
git push
git pull
```

---

# 15. Force Push ⭐

```bash
git push --force
```

Overwrites remote history.

Safer alternative:

```bash
git push --force-with-lease
```

`--force-with-lease` checks that the remote branch hasn't changed unexpectedly.

**Prefer `--force-with-lease` over `--force` when force-pushing is actually necessary.**

---

# 16. Tags

Useful for releases:

```bash
git tag v1.0.0
git push origin v1.0.0
```

Example:

```text
v1.0.0 → Production release
v1.1.0 → New features
```

---

# 🔥 Must-Memorize Git Commands

For your Spring Boot interviews, know these particularly well:

```bash
git clone
git status
git add
git commit
git push
git pull
git fetch

git branch
git switch
git merge
git rebase

git log
git diff

git stash
git cherry-pick

git reset
git revert
git restore

git tag
```

### One interview-level workflow

```bash
git clone <repo>
git switch -c feature/user-api

# Write Spring Boot code

git status
git add .
git commit -m "Add user API"

git fetch origin
git rebase origin/main

git push -u origin feature/user-api
```

Then create a **Pull Request → Code Review → Merge**.

**Most important concepts to deeply understand:** `merge vs rebase`, `reset vs revert`, `fetch vs pull`, `stash`, `cherry-pick`, conflict resolution, and `force-with-lease`.
