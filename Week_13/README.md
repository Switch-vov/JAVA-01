# 第十三周

**周三作业：**

**1.（必做）** 搭建一个 3 节点 Kafka 集群，测试功能和性能；实现 spring kafka 下对 kafka 集群的操作，将代码提交到 github。

1. 下载`kafka`，下载地址：[kafka_2.12-2.6.0.tgz](https://archive.apache.org/dist/kafka/2.6.0/kafka_2.12-2.6.0.tgz)
2. 将如下三个文件放在`kafka_2.12-2.6.0/config`下：
    - [kafka9001.properties](exercise/kafka-cluster/kafka9001.properties)
    - [kafka9002.properties](exercise/kafka-cluster/kafka9002.properties)
    - [kafka9003.properties](exercise/kafka-cluster/kafka9003.properties)
3. 在`kafka_2.12-2.6.0/config`目录下，分别使用如下命令启动
```shell
./bin/kafka-server-start.sh kafka9001.properties
./bin/kafka-server-start.sh kafka9002.properties
./bin/kafka-server-start.sh kafka9003.properties
```
4. 使用`spring kafka`操作`kafka`集群

项目地址：[kafka-spring-demo](exercise/kafka-spring-demo)

引入依赖：

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
   <groupId>org.springframework.kafka</groupId>
   <artifactId>spring-kafka</artifactId>
</dependency>
```
添加配置文件：

```yaml
spring:
  kafka:
    bootstrap-servers:
      - "localhost:9001"
      - "localhost:9002"
      - "localhost:9003"
    consumer:
      group-id: GID_TEST_SWITCH_CONSUMER
```

生产：
```java
package com.switchvov.kafka.demo.producer;

import com.alibaba.fastjson.JSON;
import com.switchvov.kafka.demo.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

/**
 * @author switch
 * @since 2021/4/23
 */
@Service
@Slf4j
public class KafkaSpringProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaSpringProducerService(
            KafkaTemplate<String, String> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTestMessage() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            Order order = new Order().setId(i).setAmount(BigDecimal.ONE).setType(1);
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("test_switch", JSON.toJSONString(order));
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.error("发送消息失败, 消息:{}", order, throwable);
                }

                @Override
                public void onSuccess(SendResult<String, String> sendResult) {
                    log.info("发送消息成功, 消息:{}, 结果:{}", order, sendResult.toString());
                }
            });
            future.get();
        }
    }
}
```

消费：
```java
package com.switchvov.kafka.demo.consumer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author switch
 * @since 2021/4/23
 */
@Component
@Slf4j
public class KafkaSpringConsumerListener {

    @KafkaListener(topics = "test_switch")
    public void listener(ConsumerRecord<String, String> data) {
        log.info("消费消息成功,topic:{},消息:{},record:{}",
                data.topic(),
                JSON.parseObject(data.value()),
                data
        );
    }
}
```

**2.（选做）** 安装 kafka-manager 工具，监控 kafka 集群状态。

**3.（挑战☆）** 演练本课提及的各种生产者和消费者特性。

**4.（挑战☆☆☆）** Kafka 金融领域实战：在证券或者外汇、数字货币类金融核心交易系统里，对于订单的处理，大概可以分为收单、定序、撮合、清算等步骤。其中我们一般可以用 mq 来实现订单定序，然后将订单发送给撮合模块。

- 收单：请实现一个订单的 rest 接口，能够接收一个订单 Order 对象；
- 定序：将 Order 对象写入到 kafka 集群的 order.usd2cny 队列，要求数据有序并且不丢失；
- 撮合：模拟撮合程序（不需要实现撮合逻辑），从 kafka 获取 order 数据，并打印订单信息，要求可重放, 顺序消费, 消息仅处理一次。

**周日作业：**

**1.（选做）** 自己安装和操作 RabbitMQ，RocketMQ，Pulsar，以及 Camel 和 Spring Integration。

**2.（必做）** 思考和设计自定义 MQ 第二个版本或第三个版本，写代码实现其中至少一个功能点，把设计思路和实现代码，提交到 GitHub。

[动手写MQ具体要求](mq.md)

第二个版本的实现：[smqTopic-core-v2](exercise/smqTopic-core-v2)

**3.（挑战☆☆☆☆☆）** 完成所有其他版本的要求。期限一年。