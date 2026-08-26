# Linux Basics — Spring Boot Developer

For Java/Spring Boot, you mainly need Linux for **running applications, checking logs, managing files, permissions, processes, and servers**. You don't need to become a Linux administrator initially.

## 1. Basic Commands

### Navigation

```bash
pwd                 # Current directory
ls                  # List files
ls -la              # List all files with details
cd /var/log         # Move to directory
cd ..               # Parent directory
cd ~                # Home directory
```

### File & Directory Operations

```bash
mkdir app            # Create directory
touch app.log        # Create empty file

cp a.txt b.txt      # Copy file
cp -r dir1 dir2     # Copy directory

mv old.txt new.txt  # Rename/move

rm file.txt         # Delete file
rm -rf mydir        # Delete directory recursively
```

⚠️ `rm -rf` is dangerous because it permanently removes files/directories.

### Reading Files

```bash
cat app.log         # Display entire file
less app.log        # Read large file
head app.log        # First 10 lines
tail app.log        # Last 10 lines
tail -f app.log     # Continuously watch new log entries
```

**Very important for Spring Boot:**

```bash
tail -f application.log
```

Useful for monitoring application logs in real time.

### Searching

```bash
grep "ERROR" application.log
```

Find files:

```bash
find . -name "*.log"
```

Example:

```bash
grep -i "exception" application.log
```

`-i` → case-insensitive search.

---

# 2. Filesystem Basics

Linux has a hierarchical filesystem:

```text
/
├── home/
├── etc/
├── var/
├── tmp/
├── usr/
└── opt/
```

Important directories:

| Directory  | Purpose                       |
| ---------- | ----------------------------- |
| `/`        | Root of filesystem            |
| `/home`    | User home directories         |
| `/etc`     | Configuration files           |
| `/var/log` | Logs                          |
| `/tmp`     | Temporary files               |
| `/usr`     | Applications/libraries        |
| `/opt`     | Optional/third-party software |

For Spring Boot, you'll commonly encounter:

```text
/etc              → configuration
/var/log          → logs
/opt              → deployed applications
/home/<user>      → user files
```

---

# 3. File Permissions

Run:

```bash
ls -l
```

Example:

```text
-rwxr-xr--  1 user developers  1200 app.sh
```

Understand the first 10 characters:

```text
- rwx r-x r--
  │   │   │
  │   │   └── Others
  │   └────── Group
  └────────── Owner
```

### Permission Types

| Permission | Meaning | Numeric |
| ---------- | ------- | ------: |
| `r`        | Read    |       4 |
| `w`        | Write   |       2 |
| `x`        | Execute |       1 |

Therefore:

```text
rwx = 4 + 2 + 1 = 7
r-x = 4 + 0 + 1 = 5
r-- = 4 + 0 + 0 = 4
```

So:

```bash
chmod 754 app.sh
```

means:

```text
Owner  → rwx → 7
Group  → r-x → 5
Others → r-- → 4
```

### Common permissions

```bash
chmod 755 script.sh
chmod 644 application.properties
```

Typical meaning:

```text
755 → Owner can read/write/execute; others can read/execute
644 → Owner can read/write; others can read
```

---

# 4. Ownership

Every file has an **owner** and **group**.

Check:

```bash
ls -l
```

Change owner:

```bash
sudo chown user:developers app.jar
```

Change group:

```bash
sudo chgrp developers app.jar
```

---

# 5. `sudo`

`sudo` runs a command with elevated privileges.

```bash
sudo systemctl restart myapp
```

You'll commonly see it when:

* Installing packages
* Modifying system configuration
* Starting/stopping services
* Changing protected files

Don't blindly use `sudo`; it bypasses normal permission restrictions.

---

# 6. Environment Variables

Very important for Spring Boot.

```bash
echo $JAVA_HOME
echo $PATH
```

Set temporarily:

```bash
export JAVA_HOME=/usr/lib/jvm/java-21
```

Then:

```bash
java -version
```

Spring Boot applications commonly use environment variables for configuration:

```bash
export DB_HOST=localhost
export DB_USERNAME=admin
```

Then Java/Spring Boot can access them.

---

# 7. Process Commands

When running Spring Boot on Linux, these are useful:

```bash
ps aux
```

Find a Java process:

```bash
ps aux | grep java
```

Kill a process:

```bash
kill <PID>
```

Force kill:

```bash
kill -9 <PID>
```

Check which process is using a port:

```bash
lsof -i :8080
```

This is especially useful when:

```text
Port 8080 already in use
```

---

# 8. Package Management

On Ubuntu/Debian:

```bash
sudo apt update
sudo apt install git
```

On RHEL/CentOS-based systems:

```bash
sudo dnf install git
```

You don't need to memorize every package command initially.

---

# 9. Essential Commands to Remember

For your **Spring Boot interviews/development**, prioritize:

```text
pwd
ls -la
cd
mkdir
touch
cp
mv
rm
cat
less
head
tail -f
grep
find
chmod
chown
sudo
ps
kill
lsof
echo
export
```

### Most important practical scenario

Suppose your Spring Boot application runs on port `8080` and isn't responding:

```bash
# 1. Check Java process
ps aux | grep java

# 2. Check port
lsof -i :8080

# 3. Check logs
tail -f application.log

# 4. Search errors
grep -i "error" application.log

# 5. If required, stop process
kill <PID>
```

**For your Spring Boot learning, focus heavily on `ls`, `cd`, `grep`, `tail`, `chmod`, `chown`, `ps`, `kill`, `lsof`, environment variables, and basic filesystem structure.**
