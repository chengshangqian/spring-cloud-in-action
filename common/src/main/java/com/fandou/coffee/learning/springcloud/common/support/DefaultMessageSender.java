package com.fandou.coffee.learning.springcloud.common.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;

public class DefaultMessageSender implements MessageSender<Object> {

    private final AmqpTemplate amqpTemplate;

    private final String queue;

    public DefaultMessageSender(AmqpTemplate amqpTemplate){
        this(amqpTemplate,MessageConstants.DEFAULT_QUEUE);
    }

    public DefaultMessageSender(AmqpTemplate amqpTemplate,String queue){
        this.amqpTemplate = amqpTemplate;
        this.queue = queue;
    }

    @Override
    public void sendMessage(Object message) {

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonMessage = null;
        try {
            jsonMessage = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

        amqpTemplate.convertAndSend(queue,jsonMessage);
    }
}
