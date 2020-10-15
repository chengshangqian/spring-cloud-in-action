package com.fandou.coffee.learning.springcloud.log.config;

import com.fandou.coffee.learning.springcloud.common.support.DefaultMessageReceiver;
import com.fandou.coffee.learning.springcloud.common.support.MessageConstants;
import com.fandou.coffee.learning.springcloud.common.support.MessageReceiver;
import com.fandou.coffee.learning.springcloud.log.service.LogService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Autowired
    private LogService logService;

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
    public MessageReceiver messageReceiver(){
        return new DefaultMessageReceiver(logService);
    }

    @Bean
    MessageListenerAdapter listenerAdapter(MessageReceiver messageReceiver){
       return new MessageListenerAdapter(messageReceiver);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(MessageConstants.DEFAULT_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }
}
