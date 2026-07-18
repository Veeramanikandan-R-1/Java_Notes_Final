# AWS EC2

### Subtopic: Application Deployment

---

# 1. Fundamentals

Amazon EC2 provides virtual machines in AWS.

For Java backend services, EC2 can run a Spring Boot JAR, a Docker container, a reverse proxy, and supporting agents. Compared with managed platforms, EC2 gives more control but also more operational responsibility.

You own:

* OS patching
* Runtime installation
* Process supervision
* Security groups
* Logs and metrics agents
* Deployment and rollback scripts

---

# 2. Core Concepts

## Instance

An EC2 instance is a VM launched from an Amazon Machine Image.

Common deployment choices:

```text
Java JAR with systemd
Docker container
AMI-based immutable deployment
Auto Scaling Group deployment
```

---

## Security Group

A security group is a virtual firewall.

Typical backend rules:

```text
Allow 22 only from trusted admin IPs
Allow 80/443 from load balancer or internet
Allow app port only from load balancer
Do not expose database ports publicly
```

---

## IAM Role

Attach an IAM role to the instance instead of storing AWS access keys on disk.

The application can then access AWS services such as S3 or CloudWatch using temporary credentials.

---

# 3. Internal Working

An EC2 deployment usually has these layers:

```text
Load Balancer -> Security Group -> EC2 instance -> Java process -> RDS/S3/other services
```

For a plain JAR deployment, `systemd` keeps the service running and restarts it on failure.

For Docker deployment, the container runtime starts the application image and externalizes config through environment variables or parameter stores.

---

# 4. Common Mistakes

* SSHing into servers and manually changing production state.
* Opening app, database, or SSH ports too broadly.
* Storing AWS keys on the instance.
* Running Java without memory limits or restart policy.
* Not sending logs and metrics to a central system.
* Deploying without health checks or rollback.
* Treating one EC2 instance as highly available.

---

# 5. Best Practices

* Use IAM roles for AWS permissions.
* Put public traffic through a load balancer.
* Keep instances private when possible.
* Automate provisioning and deployment.
* Use systemd, Docker restart policy, or orchestration for process recovery.
* Centralize logs and metrics.
* Keep security group rules minimal.
* Use Auto Scaling Groups for resilience.
* Prefer immutable deployment patterns for production.

---

# 6. Code Example

Example `systemd` unit for a Spring Boot JAR:

```ini
[Unit]
Description=Orders API
After=network.target

[Service]
User=appuser
WorkingDirectory=/opt/orders-api
Environment="SPRING_PROFILES_ACTIVE=prod"
Environment="JAVA_OPTS=-XX:MaxRAMPercentage=75"
ExecStart=/usr/bin/java $JAVA_OPTS -jar /opt/orders-api/orders-api.jar
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
```

Deploy commands:

```bash
sudo systemctl daemon-reload
sudo systemctl enable orders-api
sudo systemctl restart orders-api
sudo systemctl status orders-api
```

---

# 7. Real-world Scenarios

* Running a Spring Boot admin service on a private EC2 instance.
* Deploying Dockerized Java APIs behind an Application Load Balancer.
* Migrating manual SSH deployment to scripted deployment.
* Adding CloudWatch Agent for logs and metrics.
* Rolling back by switching to the previous JAR or image tag.

---

# Revision Notes

* EC2 is AWS virtual machine compute.
* You manage OS, runtime, deployment, and monitoring.
* Security groups control network access.
* IAM roles are preferred over static AWS keys.
* Use load balancers and health checks.
* One EC2 instance is not highly available.

---

# Cheat Sheet

| Need | Concept |
| ---- | ------- |
| VM | EC2 instance |
| Firewall | Security group |
| AWS permissions | IAM role |
| Process manager | `systemd` |
| Public entry | Load balancer |
| Resilience | Auto Scaling Group |

---

# Interview Questions with Answers

### 1. Why use an IAM role on EC2?

It provides temporary AWS credentials without storing long-lived access keys on the server.

### 2. Why put EC2 behind a load balancer?

To route traffic, perform health checks, terminate TLS, and support multiple instances.

### 3. What is the risk of manual SSH deployments?

They are hard to audit, repeat, roll back, and keep consistent across servers.

---

# Hands-on Exercises

### Exercise 1

Write a basic command to restart a Spring Boot service managed by systemd.

**Answer**

```bash
sudo systemctl restart orders-api
```

