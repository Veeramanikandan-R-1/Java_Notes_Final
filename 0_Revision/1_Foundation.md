# Env Setup Revision Notes

### Setup - Must Know

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

---

# Linux - Basics Revision Notes

Playground to practice: https://labs.iximiuz.com/playgrounds/ubuntu-26-04/6a22bea93f6cf74a0d50e76e

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