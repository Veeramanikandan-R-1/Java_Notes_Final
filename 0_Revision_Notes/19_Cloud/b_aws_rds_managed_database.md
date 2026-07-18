# Revision Notes

* RDS is managed relational database service.
* AWS manages infrastructure tasks; app teams still own schema and queries.
* Keep RDS in private subnets.
* Allow database access only from application security groups.
* Use Secrets Manager or Parameter Store for credentials.
* Use Flyway or Liquibase for migrations.
* Tune HikariCP connection pools.
* Enable backups and monitor database health.
* Multi-AZ improves availability; read replicas help reads.

---

# Cheat Sheet

| Need | RDS Concept |
| ---- | ----------- |
| Connectivity | JDBC URL |
| Firewall | Security group |
| Secret storage | Secrets Manager |
| Migration | Flyway/Liquibase |
| HA | Multi-AZ |
| Read scaling | Read replica |
| Pool | HikariCP |

---

# Interview Questions with Answers

### 1. What does RDS manage?

Database infrastructure concerns such as provisioning, backups, patching options, monitoring integration, and replication features.

### 2. Why not expose RDS publicly?

Databases should be reachable only by trusted application networks, not the public internet.

### 3. What problem do migrations solve?

They make schema changes repeatable, versioned, and consistent across environments.

---

# Hands-on Exercises

### Exercise 1

Name two tools for database migrations.

**Answer**

```text
Flyway
Liquibase
```

