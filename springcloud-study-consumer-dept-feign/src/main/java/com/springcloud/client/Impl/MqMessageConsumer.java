package com.springcloud.client.Impl;

import com.springcloud.client.MqMessageSource;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

/**
 * @author han_lic
 * @date 2021/1/18 15:42
 */
public class MqMessageConsumer {
    @StreamListener(MqMessageSource.TEST_IN_PUT)
    public void messageInPut(Message<String> message) {
        System.err.println(" 消息接收成功：" + message.getPayload());
    }
}
