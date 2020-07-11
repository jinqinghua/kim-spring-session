# Demo spring session
---

## 亮点
- 侵入性很小，可在各个实现（HttpSession, JdbcHttpSession, RedisHttpSession...）中无缝切换
- [Remember-me Support](https://docs.spring.io/spring-session/docs/current/reference/html5/index.html#spring-security-rememberme)
- [Concurrent Session Control](https://docs.spring.io/spring-session/docs/current/reference/html5/index.html#spring-security-concurrent-sessions)
    ```
    Spring Session提供与Spring Security的集成以支持其并发会话控制。这允许限制单个用户可以同时拥有的活动会话的数量，但是，与默认的Spring Security支持不同，这也适用于集群环境。
    ```
- findByIndexNameSessionRepository.findByPrincipalName 霸道， 从用户反查 Session，可做让用户下线的功能
- 比用 JWT 实现会话控制简单多了

## 坑点
- 自动配置能力太强，在 Spring Boot 中即开即用，很少的配置
- DataSource 是 Embedded DataSource 时 ，不能定制 DataSource 参数，比如 url (可能是自己功力不到，没搞定)
