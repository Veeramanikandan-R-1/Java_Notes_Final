# Service Discovery - Eureka

Act as a Principal Java Backend Engineer.

Topic To Teach: Service Discovery  
Subtopic: Eureka

## 1. Fundamentals

Service discovery lets services find each other without hardcoding hostnames and ports. In dynamic environments, instances scale up, scale down, restart, and move. Discovery provides a registry of available instances.

Eureka has two main roles:

- Eureka Server: the registry.
- Eureka Client: service instances that register themselves and discover others.

## 2. Core Concepts

### Registration

When a service starts, it registers its application name, host, port, and health metadata with Eureka.

### Heartbeat

Clients send heartbeats to prove they are alive. If heartbeats stop, Eureka eventually removes the instance.

### Discovery

Consumers ask Eureka for healthy instances of another service, then call one of those instances.

### Client-Side Load Balancing

With Spring Cloud LoadBalancer, the client chooses an instance from the registry. This avoids a central load balancer for every internal call.

## 3. Minimal Setup

Eureka server:

```java
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }
}
```

Server config:

```yaml
server:
  port: 8761

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

Client config:

```yaml
spring:
  application:
    name: order-service

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

## 4. Internal Working

Eureka clients cache registry data locally. Calls do not hit Eureka every time. This improves performance but means discovery is eventually consistent. A dead instance may remain visible briefly, so consumers still need timeouts and retries.

## 5. Common Mistakes

- Thinking Eureka replaces health checks and resilience.
- Hardcoding service URLs while also using discovery.
- Registering unstable local hostnames in containers.
- Ignoring startup order and readiness.
- Using Eureka in Kubernetes without checking if native Kubernetes service discovery is enough.

## 6. Best Practices

- Use stable `spring.application.name` values.
- Keep Eureka highly available in production.
- Use readiness probes so traffic reaches only ready services.
- Combine discovery with load balancing, timeouts, and circuit breakers.
- Do not expose Eureka dashboard publicly.
- Add metadata only when consumers truly need it.

## 7. Real-World Scenario

`order-service` needs to call `payment-service`. Instead of calling `http://localhost:8082`, it calls logical service name `payment-service`. Discovery resolves the actual instance.

```java
@Bean
RestClient.Builder restClientBuilder() {
    return RestClient.builder();
}
```

With a load-balanced client configured, the logical name can be resolved by Spring Cloud.

## Revision Notes

- Eureka is a service registry.
- Services register and send heartbeats.
- Consumers discover instances by application name.
- Registry data is cached and eventually consistent.
- Discovery must be combined with resilience patterns.

## Cheat Sheet

| Concept | Meaning |
|---|---|
| Register | Service announces itself |
| Heartbeat | Service proves it is alive |
| Fetch registry | Client downloads service list |
| Self-preservation | Eureka avoids mass eviction during network issues |
| Client-side load balancing | Consumer chooses target instance |

## Interview Q&A

**Q: Why use service discovery?**  
A: To avoid hardcoded service locations in dynamic distributed systems.

**Q: Does Eureka guarantee the instance is healthy at call time?**  
A: No. The registry can be stale, so clients still need timeouts, retries, and circuit breakers.

**Q: Eureka or Kubernetes service discovery?**  
A: In Kubernetes, native service discovery is often enough. Eureka is useful in Spring Cloud ecosystems or non-Kubernetes environments.

## Hands-on Exercises

1. Create a Eureka server on port `8761`.
2. Register `order-service` and `payment-service`.
3. Verify both services appear in the Eureka dashboard.
