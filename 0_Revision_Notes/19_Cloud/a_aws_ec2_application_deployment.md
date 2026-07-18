# Revision Notes

* EC2 provides AWS virtual machines.
* EC2 gives control but requires operational ownership.
* Use security groups to restrict network access.
* Use IAM roles instead of static AWS credentials.
* Put public traffic behind a load balancer.
* Use `systemd`, Docker, or orchestration to keep apps running.
* Centralize logs and metrics.
* Use Auto Scaling Groups or multiple instances for availability.

---

# Cheat Sheet

| Need | AWS/Server Concept |
| ---- | ------------------ |
| Virtual machine | EC2 instance |
| Firewall | Security group |
| AWS access | IAM role |
| Process manager | `systemd` |
| Entry point | Load balancer |
| Resilience | Auto Scaling Group |

---

# Interview Questions with Answers

### 1. Why should SSH access be restricted?

SSH gives administrative access, so it should be limited to trusted networks or replaced with controlled access patterns.

### 2. Why use IAM roles on EC2?

They provide temporary credentials and avoid storing long-lived AWS keys.

### 3. Why is a single EC2 instance risky?

If the instance or Availability Zone fails, the service can become unavailable.

---

# Hands-on Exercises

### Exercise 1

Check service status for a systemd-managed Java app.

**Answer**

```bash
sudo systemctl status orders-api
```

