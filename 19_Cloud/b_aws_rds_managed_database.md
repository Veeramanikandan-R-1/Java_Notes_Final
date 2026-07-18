# AWS RDS

### Subtopic: Managed Database

---

# 1. Fundamentals

Amazon RDS is a managed relational database service.

For Java backend systems, RDS commonly hosts PostgreSQL, MySQL, MariaDB, Oracle, or SQL Server databases. AWS manages infrastructure tasks such as backups, patching options, storage allocation, replication features, and monitoring integrations.

RDS reduces operational burden, but your application still owns schema design, migrations, indexing, query performance, and connection management.

---

# 2. Core Concepts

## Database Instance

An RDS instance is a managed database server.

Important configuration:

```text
engine, instance class, storage, backup retention, subnet group, security group, parameter group
```

---

## Connectivity

Java applications connect using JDBC:

```properties
spring.datasource.url=jdbc:postgresql://orders-db.xxxxxx.ap-south-1.rds.amazonaws.com:5432/orders
spring.datasource.username=orders_user
spring.datasource.password=${DB_PASSWORD}
```

The database should usually be in private subnets and reachable only from application security groups.

---

## Backups and High Availability

Automated backups enable point-in-time recovery within the configured retention period.

Multi-AZ deployments improve availability by maintaining a standby in another Availability Zone. Read replicas can help read-heavy workloads, but they introduce replication lag.

---

# 3. Internal Working

RDS runs the database engine on AWS-managed infrastructure. You interact through database endpoints, not direct server administration.

Connection pooling matters because each Java service instance can open many connections. Too many app instances or large pools can exhaust database limits.

For Spring Boot, HikariCP is commonly used as the default connection pool.

---

# 4. Common Mistakes

* Making the RDS instance publicly accessible unnecessarily.
* Oversizing connection pools.
* Running migrations manually and inconsistently.
* Ignoring slow queries and missing indexes.
* Treating read replicas as strongly consistent.
* Not testing restore procedures.
* Storing DB passwords in source code.

---

# 5. Best Practices

* Keep RDS in private subnets.
* Restrict access through security groups.
* Use Secrets Manager or Parameter Store for credentials.
* Use Flyway or Liquibase for migrations.
* Tune connection pools based on database capacity.
* Enable automated backups.
* Monitor CPU, memory, storage, connections, locks, and latency.
* Use Multi-AZ for production availability.
* Test backup restoration periodically.

---

# 6. Code Example

Spring Boot datasource configuration:

```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}:5432/orders
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.jpa.open-in-view=false
spring.flyway.enabled=true
```

Example security group rule:

```text
RDS inbound: allow TCP 5432 from application security group only
```

---

# 7. Real-world Scenarios

* Moving a self-managed PostgreSQL database to RDS.
* Debugging production connection pool exhaustion.
* Adding read replicas for reporting queries.
* Enabling Multi-AZ for a critical order service.
* Recovering from a bad migration using point-in-time restore.

---

# Revision Notes

* RDS is managed relational database hosting.
* Keep databases private.
* Use security groups for application-only access.
* Use migrations for schema changes.
* Tune Java connection pools.
* Enable backups and monitor capacity.
* Multi-AZ improves availability.

---

# Cheat Sheet

| Need | RDS Concept |
| ---- | ----------- |
| DB endpoint | JDBC host |
| Access control | Security group |
| Credentials | Secrets Manager |
| Schema changes | Flyway/Liquibase |
| Availability | Multi-AZ |
| Read scaling | Read replica |
| Pooling | HikariCP |

---

# Interview Questions with Answers

### 1. Why should RDS be private?

Databases should not be directly exposed to the internet; only application components should reach them.

### 2. What is Multi-AZ?

A high-availability setup with a standby database in another Availability Zone for failover.

### 3. Why tune connection pools?

Too many connections can overload the database, while too few can throttle application throughput.

---

# Hands-on Exercises

### Exercise 1

Write a safe RDS security group rule for PostgreSQL.

**Answer**

```text
Allow inbound TCP 5432 only from the application security group.
```

