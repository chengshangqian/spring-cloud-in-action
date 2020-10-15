package com.fandou.coffee.learning.springcloud.common.support;

public interface MessageSender<T> {
    void sendMessage(T message);
}
