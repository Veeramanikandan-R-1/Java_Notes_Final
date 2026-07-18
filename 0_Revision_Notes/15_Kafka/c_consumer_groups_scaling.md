# Revision - Consumer Groups - Scaling

## Core Recall

- Consumer group shares records among consumers.
- One partition is assigned to one consumer in a group.
- Parallelism is limited by partition count.
- Different groups receive independent copies.
- Rebalance happens when membership changes.

## Production Checklist

- Group ID per consuming application.
- Partitions sized for throughput.
- Monitor lag per group.
- Keep consumers stable.
- Avoid unnecessary rebalances.

## Mistakes

- More consumers than partitions expecting more speed.
- Same group ID for unrelated services.
- Long processing causing poll timeout.
- Partition increase without ordering review.

## Cheat Sheet

| Setup | Result |
|---|---|
| 3 partitions, 3 consumers | 3 active |
| 3 partitions, 5 consumers | 2 idle |
| Same group | Work is shared |
| Different groups | Each group gets records |

## Interview Answers

**How does Kafka scale consumers?**  
By distributing partitions across consumers in the same group.

**What is the scaling ceiling?**  
The partition count for that topic and group.

## Exercise

Run five consumers against a three-partition topic and observe assignment.
