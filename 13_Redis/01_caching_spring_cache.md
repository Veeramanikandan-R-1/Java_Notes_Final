# Caching

### Subtopic: Spring Cache

---

# 1. Fundamentals

Caching stores frequently used data so the application can avoid repeated expensive work.

Spring Cache provides an abstraction over cache providers such as Redis, Caffeine, Ehcache, and others.

In backend systems, caching is used to reduce:

* Database load
* API latency
* External service calls
* Repeated computation

Caching improves read performance, but it also introduces consistency and invalidation problems.

---

# 2. Core Concepts

| Concept | Meaning |
| ------- | ------- |
| Cache | Storage for reusable data |
| Cache key | Unique identifier for cached value |
| Cache value | Stored result |
| TTL | Time to live before expiry |
| Cache hit | Requested value found in cache |
| Cache miss | Value not found, must be loaded |
| Eviction | Removing cached value |

---

# 3. Enable Spring Cache

```java
@Configuration
@EnableCaching
class CacheConfig {
}
```

Spring creates proxies around beans that use caching annotations.

---

# 4. Cache Read with `@Cacheable`

```java
@Service
class ProductService {

    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(cacheNames = "products", key = "#id")
    ProductResponse getProduct(String id) {
        return productRepository.findById(id)
            .map(ProductResponse::from)
            .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
```

First call loads from database. Later calls with the same key return cached data until eviction or expiry.

---

# 5. Cache Update and Eviction

Use `@CacheEvict` when data changes.

```java
@Transactional
@CacheEvict(cacheNames = "products", key = "#id")
public void updateProduct(String id, UpdateProductRequest request) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException(id));

    product.changePrice(request.price());
}
```

Use `@CachePut` when you want to update cache with the method result.

```java
@CachePut(cacheNames = "products", key = "#result.id")
public ProductResponse saveProduct(CreateProductRequest request) {
    Product saved = productRepository.save(Product.from(request));
    return ProductResponse.from(saved);
}
```

---

# 6. Internal Working

Spring Cache is implemented using AOP proxies.

For `@Cacheable`:

1. Caller invokes proxied method.
2. Cache interceptor builds cache key.
3. Cache manager checks cache.
4. If key exists, cached value is returned and method body is skipped.
5. If key is missing, method runs.
6. Return value is stored in cache.
7. Value is returned to caller.

Important implication: self-invocation may bypass caching because internal method calls may not go through the proxy.

---

# 7. Redis Cache Configuration

```java
@Bean
RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofMinutes(10))
        .disableCachingNullValues();

    return RedisCacheManager.builder(connectionFactory)
        .cacheDefaults(config)
        .build();
}
```

Use different TTLs for different cache names when data freshness requirements differ.

---

# 8. Cache Key Design

Bad key:

```text
product
```

Good key:

```text
products::p123
products-by-category::electronics:page:1:size:20
```

Keys should include all inputs that affect the result:

* User ID
* Tenant ID
* Locale
* Page number
* Filter values
* Permission-sensitive dimensions

Wrong keys can leak data between users or tenants.

---

# 9. Common Mistakes

* Caching data without a clear invalidation strategy.
* Forgetting TTL.
* Caching user-specific data with a shared key.
* Caching mutable objects and accidentally modifying cached values.
* Caching errors or nulls unintentionally.
* Using cache for strongly consistent data.
* Expecting caching annotations to work on private methods.
* Calling cached methods from inside the same class.
* Not measuring hit rate and latency.

---

# 10. Best Practices

* Cache read-heavy, expensive, and acceptable-staleness data.
* Set TTL based on business freshness needs.
* Include tenant and user context in keys when required.
* Evict or update cache on writes.
* Avoid caching highly volatile data.
* Protect against cache stampede for hot keys.
* Track cache hit rate, misses, evictions, and Redis latency.
* Use local cache for ultra-hot tiny data when acceptable.
* Use Redis for shared cache across multiple app instances.

---

# 11. Production Backend Scenarios

* Product catalog pages.
* Country, city, currency, and configuration lookups.
* Feature flag snapshots.
* User permission summaries with short TTL.
* External API response caching.
* Expensive report metadata.

---

# Revision Notes

* Spring Cache abstracts cache providers.
* `@EnableCaching` enables caching support.
* `@Cacheable` reads from cache or stores method result.
* `@CacheEvict` removes stale cache entries.
* `@CachePut` updates cache with method result.
* Cache annotations work through proxies.
* TTL and key design are critical in production.

---

# Cheat Sheet

| Need | Spring Cache |
| ---- | ------------ |
| Enable cache | `@EnableCaching` |
| Read through cache | `@Cacheable` |
| Remove cache | `@CacheEvict` |
| Update cache | `@CachePut` |
| Redis cache manager | `RedisCacheManager` |
| Prevent null caching | `disableCachingNullValues()` |
| TTL | `entryTtl(...)` |

---

# Interview Questions with Answers

### 1. What is cache-aside behavior?

The application checks cache first. On miss, it loads from the source and stores the result in cache.

### 2. Why is cache invalidation hard?

Because cached data can become stale when the source data changes, especially across many keys and services.

### 3. Why include tenant ID in cache keys?

To prevent one tenant's cached data from being returned to another tenant.

### 4. Why might `@Cacheable` not work inside the same class?

Spring caching uses proxies, and self-invocation may bypass the proxy.

---

# Hands-on Exercises

### Exercise 1

Add `@Cacheable` to a method that reads product details by ID.

### Exercise 2

Add `@CacheEvict` to the product update method.

