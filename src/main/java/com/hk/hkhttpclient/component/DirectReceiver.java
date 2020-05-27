package com.hk.hkhttpclient.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : muwei
 * @ClassName:DirectReceiver
 * @Date: 2020/4/9 11:39
 * @Description: TODO
 */
@Component
@RabbitListener(queues = "test_queue")
@Slf4j
public class DirectReceiver {
    @RabbitHandler
    public void poress(Map message){
        log.info("收到消息：{}",message.toString());
    }
}
