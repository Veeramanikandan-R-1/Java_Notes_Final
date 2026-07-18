# Linux Basics (Commands, Files, Permissions)

### Senior Java Backend Engineer Learning Series

Playground to practice: https://labs.iximiuz.com/playgrounds/ubuntu-26-04/6a22bea93f6cf74a0d50e76e

---

# 1. Fundamentals

## Why Linux Matters for Backend Engineers

Most production systems run on Linux.

Examples:

```text
AWS EC2
Docker Containers
Kubernetes Nodes
PostgreSQL Servers
Kafka Brokers
Redis Servers
Spring Boot Applications
```

Even if you develop on Windows or macOS, your application will most likely run on Linux in production.

---

## Core Linux Concepts

### Everything is a File

Linux treats almost everything as a file.

```text
Regular File
Directory
Device
Network Socket
Process Information
```

Examples:

```bash
/etc/passwd
/dev/sda
/proc/cpuinfo
```

---

## Linux File System Structure

```text
/
├── bin
├── boot
├── dev
├── etc
├── home
├── lib
├── opt
├── proc
├── root
├── tmp
├── usr
└── var
```

### Important Directories

| Directory | Purpose            |
| --------- | ------------------ |
| /etc      | Configurations     |
| /var      | Logs               |
| /tmp      | Temporary files    |
| /home     | User files         |
| /opt      | Applications       |
| /usr      | Installed software |
| /proc     | System information |

---

## Essential Commands

### Current Directory

```bash
pwd
```

Example:

```bash
/opt/apps/order-service
```

---

### List Files

```bash
ls
```

Detailed:

```bash
ls -la
```

---

### Change Directory

```bash
cd
```

Examples:

```bash
cd /var/log
cd ..
cd ~
```

---

### Create Directory

```bash
mkdir project
```

---

### Create File

```bash
touch app.log
```

---

### Copy

```bash
cp source.txt destination.txt
```

---

### Move

```bash
mv old.txt new.txt
```

---

### Delete

```bash
rm file.txt
rm -rf directory
```

Be careful:

```bash
rm -rf /
```

is catastrophic.

---

# 2. Internal Working

---

## Linux Kernel

Architecture:

```text
Applications
     |
System Calls
     |
Linux Kernel
     |
Hardware
```

The kernel manages:

* CPU
* Memory
* Disk
* Network
* Processes

---

## System Calls

Example:

When Java reads a file:

```java
Files.readString(path);
```

Internally:

```text
Java
 ↓
JVM
 ↓
Linux System Call
 ↓
Kernel
 ↓
Disk
```

---

## Process Management

Every running program is a process.

View processes:

```bash
ps -ef
```

Find Java processes:

```bash
ps -ef | grep java
```

---

# 3. Production Usage

Backend engineers use Linux daily.

### Deploy Spring Boot

```bash
java -jar app.jar
```

---

### Monitor Logs

```bash
tail -f application.log
```

---

### Check Running Service

```bash
ps -ef | grep app
```

---

### Kill Process

```bash
kill -9 PID
```

---

### Monitor Resources

```bash
top
```

or

```bash
htop
```

---

# 4. Performance Considerations

---

## CPU Monitoring

```bash
top
```

Shows:

```text
CPU
Memory
Processes
Load Average
```

---

## Disk Usage

```bash
df -h
```

Example:

```text
Filesystem Size Used Avail
```

---

## Directory Size

```bash
du -sh logs/
```

---

## Network Usage

```bash
netstat -tulpn
```

or

```bash
ss -tulpn
```

---

# 5. Memory Considerations

---

## View Memory

```bash
free -h
```

Output:

```text
Total
Used
Free
Available
```

---

## Java Memory

Check:

```bash
jcmd PID VM.flags
```

or

```bash
jmap -heap PID
```

---

## Memory Leaks

Symptoms:

```text
High RAM
Slow response
OOM Errors
```

Linux tools help diagnose issues.

---

# 6. Security Considerations

---

## Linux Users

Check:

```bash
whoami
```

---

## Switch User

```bash
sudo su -
```

exit -> to revert sudo su command

---

## Principle of Least Privilege

Bad:

```bash
chmod 777
```

Good:

```bash
chmod 644
chmod 755
```

---

## SSH Access

Connect:

```bash
ssh user@server
```

Prefer:

```text
SSH Keys
```

instead of passwords.

---

## Firewall

Ubuntu:

```bash
ufw status
```

---

# 7. Spring Boot Usage

---

### Run Application

```bash
java -jar app.jar
```

---

### Background Execution

```bash
nohup java -jar app.jar &
```

---

### Environment Variables

```bash
export DB_PASSWORD=secret
```

Spring Boot:

```properties
spring.datasource.password=${DB_PASSWORD}
```

---

### View Logs

```bash
tail -f application.log
```

---

### Service Management

Systemd:

```bash
systemctl start app
systemctl stop app
systemctl restart app
```

---

# 8. Database Impact

---

## PostgreSQL Service

Check:

```bash
systemctl status postgresql
```

---

## Connect

```bash
psql -U postgres
```

---

## Backup

```bash
pg_dump
```

---

## Restore

```bash
pg_restore
```

---

## Monitor Connections

```sql
SELECT * FROM pg_stat_activity;
```

---

# 9. Common Mistakes

| Mistake              | Impact               |
| -------------------- | -------------------- |
| chmod 777            | Security risk        |
| Running as root      | Dangerous            |
| Not checking logs    | Slow troubleshooting |
| Deleting wrong files | Data loss            |
| Ignoring disk space  | Service failures     |
| Hardcoding passwords | Security issue       |
| Not rotating logs    | Disk full            |

---

# 10. Best Practices

### Use SSH Keys

```text
Preferred over passwords
```

---

### Use Non-Root Users

```text
Application users only
```

---

### Monitor Logs

```bash
tail -f
```

---

### Check Disk Usage Regularly

```bash
df -h
```

---

### Use Environment Variables

Never:

```properties
password=mysecret
```

Always:

```bash
export DB_PASSWORD
```

`chmod` is a command in Unix/Linux systems used to **change the permissions** of files and directories. The name comes from **“change mode.”**

### What permissions mean

Every file has three types of access:

* **Read (r)** – view the contents
* **Write (w)** – modify the file
* **Execute (x)** – run the file as a program

And these permissions apply to three groups:

* **User (u)** – the file owner
* **Group (g)** – users in the same group
* **Others (o)** – everyone else

---

## Basic syntax

```
chmod [options] permissions file
```

---

## Two common ways to use `chmod`

### 1. Symbolic mode (letters)

You specify *who* and *what* to change.

Example:

```
chmod u+x script.sh
```

➡ Adds execute permission to the owner.

```
chmod go-w file.txt
```

➡ Removes write permission from group and others.

---

### 2. Numeric (octal) mode

Each permission is represented by a number:

* Read = 4
* Write = 2
* Execute = 1

Add them together:

* 7 = rwx (4+2+1)
* 6 = rw- (4+2)
* 5 = r-x (4+1)
* 4 = r--

Example:

```
chmod 755 script.sh
```

➡ Owner: rwx (7)
➡ Group: r-x (5)
➡ Others: r-x (5)

```
chmod 644 file.txt
```

➡ Owner: rw-
➡ Group: r--
➡ Others: r--

---

## Common use cases

* Make a script executable:

  ```
  chmod +x script.sh
  ```
* Restrict access to a private file:

  ```
  chmod 600 secret.txt
  ```
* Allow everyone to read a file:

  ```
  chmod 644 file.txt
  ```

---

## Quick mental model

Think of `chmod` as:

> “Who is allowed to do what with this file?”

---

# 11. Interview Questions

### Q1

What is Linux?

Answer:

Open-source Unix-like operating system.

---

### Q2

Difference between:

```bash
cp
```

and

```bash
mv
```

Answer:

Copy vs Move.

---

### Q3

What does:

```bash
chmod 755
```

mean?

Answer:

```text
Owner: rwx
Group: r-x
Others: r-x
```

---

### Q4

How do you find a Java process?

Answer:

```bash
ps -ef | grep java
```

---

### Q5

How do you monitor logs?

Answer:

```bash
tail -f app.log
```

---

### Q6

What is sudo?

Answer:

Execute commands with elevated privileges.

---

### Q7

How do you check memory usage?

Answer:

```bash
free -h
```

---

### Q8

What is nohup?

Answer:

Keeps process running after terminal closes.

---

### Q9

How do you identify open ports?

Answer:

```bash
ss -tulpn
```

---

### Q10

Why should applications not run as root?

Answer:

Security principle.

---

# 12. Code Examples

---

## Find Log Errors

```bash
grep ERROR application.log
```

---

## Search Files

```bash
find . -name "*.log"
```

---

## Count Lines

```bash
wc -l application.log
```

---

## Monitor Java Process

```bash
top -p PID
```

---

## Check Open Port

```bash
ss -tulpn | grep 8080
```

---

# 13. Real-World Scenarios

## E-Commerce

Problem:

```text
Checkout API slow
```

Engineer:

```bash
top
free -h
tail -f logs
```

Finds CPU bottleneck.

---

## Banking

Problem:

```text
Transaction service down
```

Check:

```bash
systemctl status
```

Restart service.

---

## Healthcare

Problem:

```text
Disk full
```

Check:

```bash
df -h
```

Clean logs.

---

## SaaS

Problem:

```text
Database unavailable
```

Check:

```bash
systemctl status postgresql
```

---

## Social Media

Problem:

```text
Kafka consumer stopped
```

Check:

```bash
ps -ef
```

Restart service.

---

# Revision Notes

| Topic   | Key Point         |
| ------- | ----------------- |
| pwd     | Current directory |
| ls -la  | List files        |
| cd      | Change directory  |
| mkdir   | Create directory  |
| touch   | Create file       |
| cp      | Copy              |
| mv      | Move              |
| rm      | Delete            |
| ps -ef  | Processes         |
| top     | CPU monitoring    |
| free -h | Memory monitoring |
| df -h   | Disk monitoring   |
| tail -f | Log monitoring    |
| chmod   | Permissions       |
| ssh     | Remote access     |

---

# One-Page Cheat Sheet

```text
Navigation

pwd
ls -la
cd

Files

touch
mkdir
cp
mv
rm

Monitoring

top
free -h
df -h

Processes

ps -ef
kill -9 PID

Logs

tail -f app.log
grep ERROR app.log

Permissions

chmod 755
chmod 644

Networking

ss -tulpn

Spring Boot

java -jar app.jar
nohup java -jar app.jar &
```

---

# Flashcards

### Q1

What command shows current directory?

A: `pwd`

### Q2

What command lists hidden files?

A: `ls -la`

### Q3

How do you monitor logs continuously?

A: `tail -f`

### Q4

How do you view memory usage?

A: `free -h`

### Q5

How do you check disk usage?

A: `df -h`

### Q6

How do you find Java processes?

A: `ps -ef | grep java`

### Q7

What does chmod 755 mean?

A: Owner rwx, Group r-x, Others r-x

### Q8

Why avoid chmod 777?

A: Security risk

### Q9

What is nohup used for?

A: Run process after terminal closes

### Q10

How do you connect to a Linux server?

A: `ssh user@host`

---

# Active Recall Questions

1. Explain Linux file permissions.
2. Difference between chmod 755 and 644?
3. How does Java interact with Linux kernel?
4. How do you troubleshoot a slow Spring Boot application?
5. Which Linux commands do you use daily as a backend engineer?
6. How would you investigate high CPU usage?
7. How would you investigate high memory usage?
8. How do you securely store secrets in Linux?
9. How do you restart a Spring Boot service?
10. How do you monitor PostgreSQL on Linux?

---

# Hands-On Exercises

### Easy

* Navigate directories
* Create files
* Copy files
* Move files

### Medium

* Create users
* Change permissions
* Monitor processes

### Hard

* Deploy Spring Boot app using `nohup`
* Configure environment variables
* Monitor logs

### Production-Level Task

Create a Linux VM (Ubuntu preferred) and:

```text
Install:
- Java 21
- PostgreSQL
- Git

Deploy:
- Spring Boot Application

Configure:
- Environment Variables
- Application Logs
- Service Startup

Monitor:
- CPU
- Memory
- Disk
- Application Logs
```

This is very close to what you'll actually do as a backend engineer managing services in development, staging, and production environments.
