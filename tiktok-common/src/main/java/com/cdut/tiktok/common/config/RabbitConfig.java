package com.cdut.tiktok.common.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // 定义队列名称
    private static final String QUEUE_GIFTORCOMMENT = "queue_giftorcomment";

    // 定义交换机名称
    private static final String EXCHANGE = "livestreamexchange";

    // 定义路由键
    private static final String ROUTING_KEY_GIFTORCOMMENT = "my_routing_key_giftorcomment";

    @Bean
    Queue queue() {
        return new Queue(QUEUE_GIFTORCOMMENT, true);  // true表示持久化该队列
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_GIFTORCOMMENT);
    }

}

