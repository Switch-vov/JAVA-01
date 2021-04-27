# 第十一周

**周三作业：**

**1.（选做）** 按照课程内容，动手验证 Hibernate 和 Mybatis 缓存。

**2.（选做）** 使用 spring 或 guava cache，实现业务数据的查询缓存。

**3.（挑战☆）** 编写代码，模拟缓存穿透，击穿，雪崩。

**4.（挑战☆☆）** 自己动手设计一个简单的 cache，实现过期策略。

**周日作业：**

**1.（选做）** 命令行下练习操作 Redis 的各种基本数据结构和命令。

**2.（选做）** 分别基于 jedis，RedisTemplate，Lettuce，Redission 实现 redis 基本操作的 demo，可以使用 spring-boot 集成上述工具。

**3.（选做）** spring 集成练习:
- 实现 update 方法，配合 @CachePut
- 实现 delete 方法，配合 @CacheEvict
- 将示例中的 spring 集成 Lettuce 改成 jedis 或 redisson

**4.（必做）** 基于 Redis 封装分布式数据操作：

项目：[redis-demo](exercise/redis-demo)

- 在 Java 中实现一个简单的分布式锁；

核心类：
```java
package com.switchvov.redis.demo.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁 helper类
 *
 * @author switch
 * @since 2021/4/27
 */
@Slf4j
public class CLockHelper implements ApplicationContextAware {
    private static RedissonClient client;

    public static RLock getLock(String lockKey) {
        return client.getLock(lockKey);
    }

    public static boolean tryLock(RLock lock, int waitTime, int leaseTime, TimeUnit unit) {
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    public static void unlock(RLock lock) {
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        client = applicationContext.getBean(RedissonClient.class);
    }
}
```

- 在 Java 中实现一个分布式计数器，模拟减库存。

核心类：
```java
package com.switchvov.redis.demo.atomic;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Objects;

/**
 * @author switch
 * @since 2021/4/27
 */
public class RedisAtomicLong {
    private final StringRedisTemplate template;

    public RedisAtomicLong(
            RedisConnectionFactory factory
    ) {
        // 创建一个template
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(factory);
        template.afterPropertiesSet();
        this.template = template;
    }

    public Long inc(String key, long delta) {
        BoundValueOperations<String, String> valueOps = template.boundValueOps(key);
        return valueOps.increment(delta);
    }

    public Long dec(String key, long delta) {
        return inc(key, -delta);
    }

    public Long get(String key) {
        BoundValueOperations<String, String> valueOps = template.boundValueOps(key);
        return Long.valueOf(Objects.requireNonNull(valueOps.get()));
    }
}
```


**5.（必做）** 基于 Redis 的 PubSub 实现订单异步处理

**6.（挑战☆）** 基于其他各类场景，设计并在示例代码中实现简单 demo：
- 实现分数排名或者排行榜；
- 实现全局 ID 生成；
- 基于 Bitmap 实现 id 去重；
- 基于 HLL 实现点击量计数；
- 以 redis 作为数据库，模拟使用 lua 脚本实现前面课程的外汇交易事务。

**7.（挑战☆☆）** 升级改造项目：
- 实现 guava cache 的 spring cache 适配；
- 替换 jackson 序列化为 fastjson 或者 fst，kryo；
- 对项目进行分析和性能调优。

**8.（挑战☆☆☆）** 以 redis 作为基础实现上个模块的自定义 rpc 的注册中心。