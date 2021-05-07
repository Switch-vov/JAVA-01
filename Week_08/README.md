# 第八周

**周三作业：**

**1.（选做）** 分析前面作业设计的表，是否可以做垂直拆分。

**2.（必做）** 设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。

项目地址：[proxy](exercise/proxy)

README文档：[proxy - README.md](exercise/proxy/README.md)

关键配置：读写分离 + 数据分片

```yaml
schemaName: order

dataSources:
  ds_0:
    url: jdbc:mysql://192.168.199.222:13300/order_0?serverTimezone=UTC&useSSL=false
    username: root
    password: root
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 50
  ds_1:
    url: jdbc:mysql://192.168.199.222:13300/order_1?serverTimezone=UTC&useSSL=false
    username: root
    password: root
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 50
  ds_0_slave1:
    url: jdbc:mysql://192.168.199.222:23300/order_0?serverTimezone=UTC&useSSL=false
    username: root
    password: root
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 50
  ds_0_slave2:
    url: jdbc:mysql://192.168.199.222:33300/order_0?serverTimezone=UTC&useSSL=false
    username: root
    password: root
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 50
  ds_1_slave1:
    url: jdbc:mysql://192.168.199.222:23300/order_1?serverTimezone=UTC&useSSL=false
    username: root
    password: root
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 50
  ds_1_slave2:
    url: jdbc:mysql://192.168.199.222:33300/order_1?serverTimezone=UTC&useSSL=false
    username: root
    password: root
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 50
rules:
  - !SHARDING
    tables:
      order:
        actualDataNodes: rw_ds_${0..1}.order_${0..15}
        tableStrategy:
          standard:
            shardingColumn: user_id
            shardingAlgorithmName: order_inline
        # keyGenerateStrategy:
        #   column: order_id
    bindingTables:
      - order
    defaultDatabaseStrategy:
      standard:
        shardingColumn: user_id
        shardingAlgorithmName: database_inline
    defaultTableStrategy:
      none:
    shardingAlgorithms:
      database_inline:
        type: INLINE
        props:
          algorithm-expression: rw_ds_${user_id % 2}
      order_inline:
        type: INLINE
        props:
          algorithm-expression: order_${user_id % 16}
  - !READWRITE_SPLITTING
    dataSources:
      rw_ds_0:
        name: rw_ds_0 # 读写分离的逻辑数据源名称 `rw_ds_0` 用于在数据分片中使用
        writeDataSourceName: ds_0 # 使用真实存在的数据源名称 `ds_0`
        readDataSourceNames:
          - ds_0_slave1 # 使用真实存在的数据源名称 `ds_0_slave1`
          - ds_0_slave2 # 使用真实存在的数据源名称 `ds_0_slave2`
        loadBalancerName: roundRobin
      rw_ds_1:
        name: rw_ds_1 # 读写分离的逻辑数据源名称 `rw_ds_1` 用于在数据分片中使用
        writeDataSourceName: ds_1 # 使用真实存在的数据源名称 `ds_1`
        readDataSourceNames:
          - ds_1_slave1 # 使用真实存在的数据源名称 `ds_1_slave1`
          - ds_1_slave2 # 使用真实存在的数据源名称 `ds_1_slave2`
        loadBalancerName: roundRobin
    loadBalancers:
      roundRobin:
        type: ROUND_ROBIN
```

**3.（选做）** 模拟 1000 万的订单单表数据，迁移到上面作业 2 的分库分表中。

**4.（选做）** 重新搭建一套 4 个库各 64 个表的分库分表，将作业 2 中的数据迁移到新分库。

**周日作业：**

**1.（选做）** 列举常见的分布式事务，简单分析其使用场景和优缺点。

**2.（必做）** 基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），提交到 Github。

1. 使用SharingSphere Proxy
    - Proxy搭建地址：[proxy](exercise/proxy)
    - 更改：[server.yaml](exercise/proxy/conf/server.yaml)
```yaml
users:
  - root@%:root

props:
  max-connections-size-per-query: 1
  executor-size: 16  # Infinite by default.
  proxy-frontend-flush-threshold: 128  # The default value is 128.
    # LOCAL: Proxy will run with LOCAL transaction.
    # XA: Proxy will run with XA transaction.
    # BASE: Proxy will run with B.A.S.E transaction.
  proxy-transaction-type: XA
  proxy-opentracing-enabled: false
  xa-transaction-manager-type: Atomikos
  sql-show: true
  sql-simple: false
```
2. TODO：使用SharingSphere JDBC的demo
   - 会参考 [ShardingSphere RAW JDBC 分布式事务XA 代码示例](https://blog.csdn.net/github_35735591/article/details/110734467) 搭建

**3.（选做）** 基于 ShardingSphere narayana XA 实现一个简单的分布式事务 demo。

**4.（选做）** 基于 seata 框架实现 TCC 或 AT 模式的分布式事务 demo。

**5.（选做☆）** 设计实现一个简单的 XA 分布式事务框架 demo，只需要能管理和调用 2 个 MySQL 的本地事务即可，不需要考虑全局事务的持久化和恢复、高可用等。

**6.（选做☆）** 设计实现一个 TCC 分布式事务框架的简单 Demo，需要实现事务管理器，不需要实现全局事务的持久化和恢复、高可用等。

**7.（选做☆）** 设计实现一个 AT 分布式事务框架的简单 Demo，仅需要支持根据主键 id 进行的单个删改操作的 SQL 或插入操作的事务。
