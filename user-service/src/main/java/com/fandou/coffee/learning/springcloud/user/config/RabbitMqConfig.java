package com.fandou.coffee.learning.springcloud.user.config;

import com.fandou.coffee.learning.springcloud.common.support.DefaultMessageSender;
import com.fandou.coffee.learning.springcloud.common.support.DoLogAspect;
import com.fandou.coffee.learning.springcloud.common.support.MessageConstants;
import com.fandou.coffee.learning.springcloud.common.support.MessageSender;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    Queue queue(){
        return new Queue(MessageConstants.DEFAULT_QUEUE,false);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(MessageConstants.DEFAULT_TOPIC_EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(MessageConstants.DEFAULT_QUEUE);
    }

    @Bean
    MessageSender messageSender(AmqpTemplate amqpTemplate){
        return new DefaultMessageSender(amqpTemplate);
    }

    /**
     * 消息日志
     * @param messageSender 消息发送者
     * @return
     */
    @Bean
    DoLogAspect doLogAspect(MessageSender messageSender){
        return new DoLogAspect(messageSender);
    }
}
