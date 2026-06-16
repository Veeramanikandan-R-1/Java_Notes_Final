# Revision Notes

* for → known iteration count
* while → unknown iteration count
* do-while → execute at least once
* Enhanced for uses Iterator internally
* Avoid loading huge collections
* Prefer pagination and streaming
* Watch for N+1 query issues
* Avoid nested loops on large datasets
* Keep loop bodies lightweight
* Always ensure termination conditions
* Retry loops require limits
* Infinite loops can become production incidents

---

# Cheat Sheet

```java
for(int i=0;i<n;i++)
```

```java
for(Item item : items)
```

```java
while(condition)
```

```java
do {
}
while(condition);
```

```java
Iterator<T> iterator = list.iterator();
```

```java
while(iterator.hasNext()) {
    T item = iterator.next();
}
```

```java
Page<T> page
```

```java
Stream<T> stream
```

---

# Interview Flashcards

### Q: When use for vs while?

**A:** Use for when iteration count is known. Use while when termination condition is dynamic or unknown.

---

### Q: How does enhanced for work internally?

**A:** Compiler converts it to Iterator-based traversal.

---

### Q: Why is findAll() dangerous?

**A:** Loads entire dataset into memory, causing OOM risks.

---

### Q: What is the N+1 problem?

**A:** Loop accesses lazily loaded entities causing one query per iteration.

---

### Q: Why avoid nested loops?

**A:** Often introduces O(n²) complexity.

---

### Q: Why is do-while rarely used in backend systems?

**A:** Most backend processing requires condition evaluation before execution.

---

### Q: What causes ConcurrentModificationException?

**A:** Modifying a collection while iterating through it using enhanced for or an iterator improperly.

---

### Q: Why use pagination?

**A:** Controls memory consumption and improves scalability.

---

# Active Recall Questions (with Answers)

### 1. Which loop guarantees at least one execution?

**Answer:** do-while.

---

### 2. What does enhanced for compile into?

**Answer:** Iterator-based traversal.

---

### 3. What database issue commonly appears inside loops?

**Answer:** N+1 query problem.

---

### 4. Why are nested loops dangerous?

**Answer:** Time complexity can grow to O(n²) or worse.

---

### 5. What should be used instead of processing 10 million rows in memory?

**Answer:** Pagination, streaming, chunk processing.

---

### 6. Why should retry loops have limits?

**Answer:** Prevent infinite retries and retry storms.

---

### 7. What exception occurs when modifying a collection during iteration?

**Answer:** ConcurrentModificationException.

---

### 8. What is the preferred loop for traversing collections?

**Answer:** Enhanced for-loop when index is not needed.

---

# Hands-On Exercises

## Exercise 1 — Retry Logic

Implement:

```java
callExternalService()
```

Retry 3 times before failing.

### Answer

```java
for(int attempt = 1; attempt <= 3; attempt++) {

    try {
        callExternalService();
        break;

    } catch(Exception ex) {

        if(attempt == 3)
            throw ex;
    }
}
```

---

## Exercise 2 — Paginated Customer Processing

Process customers in pages of 1000.

### Answer

```java
int page = 0;

Page<Customer> customers;

do {

    customers =
        repository.findAll(PageRequest.of(page++,1000));

    for(Customer customer : customers) {
        process(customer);
    }

} while(customers.hasNext());
```

---

## Exercise 3 — Remove Invalid Orders Safely

Given:

```java
List<Order> orders;
```

Remove cancelled orders.

### Answer

```java
Iterator<Order> iterator = orders.iterator();

while(iterator.hasNext()) {

    Order order = iterator.next();

    if(order.isCancelled()) {
        iterator.remove();
    }
}
```

---

## Exercise 4 — Detect N+1 Query Problem

Given:

```java
for(Order order : orders) {
    System.out.println(order.getItems().size());
}
```

**Answer:** `items` may be lazily loaded, causing one query per order. Fix using `JOIN FETCH` or `@EntityGraph`.

---

## Exercise 5 — Convert Index Loop to Enhanced For

Input:

```java
for(int i=0;i<orders.size();i++) {
    process(orders.get(i));
}
```

### Answer

```java
for(Order order : orders) {
    process(order);
}
```
