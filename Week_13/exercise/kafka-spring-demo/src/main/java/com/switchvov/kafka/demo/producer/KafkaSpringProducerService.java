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
