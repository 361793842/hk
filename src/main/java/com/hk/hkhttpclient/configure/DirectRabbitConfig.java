package com.hk.hkhttpclient.configure;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author : muwei
 * @ClassName:DirectRabbitConfig
 * @Date: 2020/4/9 11:36
 * @Description: TODO
 */
//@Configuration
public class DirectRabbitConfig {
    // 测试队列名称
    private String testQueueName = "test_queue";
    // 测试交换机名称
    private String testExchangeName = "test_exchange";
    // RoutingKey
    private String testRoutingKey = "test_routing_key";

    /** 创建队列 */
    @Bean
    public Queue testQueue() {
        return new Queue(testQueueName);
    }
    /** 创建交换机 */
    @Bean
    public TopicExchange testExchange() {
        return new TopicExchange(testExchangeName);
    }
    /** 通过routingKey把队列与交换机绑定起来 */
    @Bean
    public Binding testBinding() {
        return BindingBuilder.bind(testQueue()).to(testExchange()).with(testRoutingKey);
    }
}
