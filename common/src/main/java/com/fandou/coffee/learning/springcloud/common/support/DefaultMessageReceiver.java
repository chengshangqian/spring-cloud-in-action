package com.fandou.coffee.learning.springcloud.common.support;

import com.fandou.coffee.learning.springcloud.common.model.Log;
import com.fandou.coffee.learning.springcloud.common.service.LogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class DefaultMessageReceiver implements MessageReceiver {

    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMessageReceiver.class);

    // 栈栏
    private final CountDownLatch latch = new CountDownLatch(1);

    private final LogService logService;

    public DefaultMessageReceiver(LogService logService) {
        assert null != logService;
        this.logService = logService;
    }

    @Override
    public void handleMessage(String message) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("收到日志消息 => {}", message);
        }

        Log log = null;
        try {
            log = new ObjectMapper().readerFor(Log.class).readValue(message);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("log => {}", log);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

        logService.create(log);

        latch.countDown();
    }
}
