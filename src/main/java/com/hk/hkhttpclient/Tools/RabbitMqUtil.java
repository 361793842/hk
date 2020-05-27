package com.hk.hkhttpclient.Tools;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/*import com.rabbitmq.client.QueueingConsumer;*/
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author : muwei
 * @ClassName:ConnectionUtil
 * @Date: 2020/4/2 14:40
 * @Description: TODO
 */

/*public class RabbitMqUtil {
    private static final String QUEUE_NAME="q_test_001";
    *//**
     * 功能描述:连接服务
     * @param:
     * @return:
     * @auther: mw
     * @date: 2020/4/2 14:44
     *//*
    public static Connection getConnection() throws IOException {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("localhost");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
       // factory.setVirtualHost("testhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
    *//**
     * 功能描述:发送消息
     * @param:
     * @return:
     * @auther: mw
     * @date: 2020/4/2 14:44
     *//*
    public static void send(String msg){
        try {
            //获取到连接以及mq通道
            Connection connection = RabbitMqUtil.getConnection();
            //从连接中创建通道
            Channel channel=connection.createChannel();
            //声明创建队列
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            //发送消息
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            log.info("DS -> MSO: "+msg);
            //关闭通道和连接
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void recvMsg(){
        // 获取到连接以及mq通道
        Connection connection = null;
        try {
            connection = RabbitMqUtil.getConnection();
            // 从连接中创建通道
            Channel channel = connection.createChannel();
            // 声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 定义队列的消费者
            QueueingConsumer consumer = new QueueingConsumer(channel);
            // 监听队列
            channel.basicConsume(QUEUE_NAME, true, consumer);
            // 获取消息
            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                log.info("DS <- MSO: "+message);
            }
        } catch (IOException | InterruptedException e) {
            log.error("sdsdsdsd");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            send("数据测试："+i);
        }
    }
}*/
