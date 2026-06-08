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
* Git uses DAG internally
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