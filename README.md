# Demo spring session
---

## 亮点
- 侵入性很小，在各个 HttpSession, JdbcHttpSession, RedisHttpSession...等实现无缝切换
- [Remember me 功能支持](https://docs.spring.io/spring-session/docs/current/reference/html5/index.html#spring-security-rememberme)
- [Session 并发数控制](https://docs.spring.io/spring-session/docs/current/reference/html5/index.html#spring-security-concurrent-sessions)
    1. 允许限制单个用户可以同时拥有的并发会话的数量
    2. 集群环境也支持
- findByIndexNameSessionRepository.findByPrincipalName 霸道， 从用户反查 Session，可做让用户下线的功能
- 比用 JWT 实现会话控制简单多了

## 坑点
- 自动配置能力太强，在 Spring boot 中即开即用，很少的配置
- Embedded DataSource, 不能 Custom DataSource (可能是自己功力不到，没搞定)
