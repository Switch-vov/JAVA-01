# 第十二周

**周三作业：**

**1.（必做）** 配置 redis 的主从复制，sentinel 高可用，Cluster 集群。

配置文件：[redis](exercise/redis)

`server`、`sentinel`、`cluster`每个目录下都有一个`docker-compose.yaml`文件，进入相应目录，用`docker-compose up -d`能够启动

1. `server`: 主从复制
2. `server` + `sentinel`: `sentinel`高可用
3. `cluster`: `cluster`集群

目录结构如下：

```
.
└── redis
    ├── cluster
    │   ├── cluster_create.sh
    │   ├── config
    │   │   ├── 16380
    │   │   │   └── redis.conf
    │   │   ├── 16381
    │   │   │   └── redis.conf
    │   │   ├── 16382
    │   │   │   └── redis.conf
    │   │   ├── 26380
    │   │   │   └── redis.conf
    │   │   ├── 26381
    │   │   │   └── redis.conf
    │   │   └── 26382
    │   │       └── redis.conf
    │   ├── data
    │   └── docker-compose.yaml
    ├── sentinel
    │   ├── README.md
    │   ├── config
    │   │   ├── sentinel_6370.conf
    │   │   ├── sentinel_6371.conf
    │   │   └── sentinel_6372.conf
    │   ├── data
    │   └── docker-compose.yaml
    └── server
        ├── config
        │   ├── redis_16370.conf
        │   ├── redis_26370.conf
        │   └── redis_36370.conf
        ├── data
        └── docker-compose.yaml
```

**2.（选做）**  练习示例代码里下列类中的作业题:
`08cache/redis/src/main/java/io/kimmking/cache/RedisApplication.java`

**3.（选做☆）** 练习 redission 的各种功能。

**4.（选做☆☆）** 练习 hazelcast 的各种功能。

**5.（选做☆☆☆）** 搭建 hazelcast 3 节点集群，写入 100 万数据到一个 map，模拟和演 示高可用。

**周日作业：**

**1.（必做）** 搭建 ActiveMQ 服务，基于 JMS，写代码分别实现对于 queue 和 topic 的消息生产和消费，代码提交到 github。

**2.（选做）** 基于数据库的订单表，模拟消息队列处理订单：

- 一个程序往表里写新订单，标记状态为未处理 (status=0);
- 另一个程序每隔 100ms 定时从表里读取所有 status=0 的订单，打印一下订单数据，然后改成完成 status=1；
- （挑战☆）考虑失败重试策略，考虑多个消费程序如何协作。

**3.（选做）** 将上述订单处理场景，改成使用 ActiveMQ 发送消息处理模式。

**4.（选做）** 使用 java 代码，创建一个 ActiveMQ Broker Server，并测试它。

**5.（挑战☆☆）** 搭建 ActiveMQ 的 network 集群和 master-slave 主从结构。

**6.（挑战☆☆☆）** 基于 ActiveMQ 的 MQTT 实现简单的聊天功能或者 Android 消息推送。

**7.（挑战☆）** 创建一个 RabbitMQ，用 Java 代码实现简单的 AMQP 协议操作。

**8.（挑战☆☆）** 搭建 RabbitMQ 集群，重新实现前面的订单处理。

**9.（挑战☆☆☆）** 使用 Apache Camel 打通上述 ActiveMQ 集群和 RabbitMQ 集群，实现所有写入到 ActiveMQ 上的一个队列 q24 的消息，自动转发到 RabbitMQ。

**10.（挑战☆☆☆）** 压测 ActiveMQ 和 RabbitMQ 的性能。