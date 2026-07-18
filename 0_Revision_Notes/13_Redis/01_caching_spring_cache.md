# Revision Notes

* Spring Cache abstracts cache providers such as Redis and Caffeine.
* `@EnableCaching` enables cache annotation support.
* `@Cacheable` returns cached data when available and skips method execution.
* `@CacheEvict` removes stale entries.
* `@CachePut` updates the cache with the method result.
* Caching annotations work through Spring proxies.
* Good cache key design must include tenant, user, filters, and other result-changing inputs.
* TTL and invalidation strategy are required for production caching.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Enable caching | `@EnableCaching` |
| Cache read | `@Cacheable` |
| Evict cache | `@CacheEvict` |
| Update cache | `@CachePut` |
| Redis cache config | `RedisCacheManager` |
| TTL | `entryTtl(...)` |
| Avoid null values | `disableCachingNullValues()` |

---

# Interview Q&A

### 1. What is a cache hit?

The requested value is found in cache and does not need to be loaded from the original source.

### 2. Why is cache invalidation difficult?

Data can change in the source while stale copies still exist in cache.

### 3. Why can self-invocation break caching?

Spring Cache uses proxies, and internal method calls may bypass the proxy.

---

# Exercises

### Exercise 1

Add `@Cacheable(cacheNames = "products", key = "#id")` to a product lookup method.

### Exercise 2

Evict product cache when product price changes.

