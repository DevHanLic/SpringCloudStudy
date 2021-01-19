package com.springcloud.client;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author han_lic
 * @date 2021/1/18 15:41
 */
public interface MqMessageSource {

    String TEST_IN_PUT = "testInPut";

    @Input(TEST_IN_PUT)
    SubscribableChannel testInPut();
}
