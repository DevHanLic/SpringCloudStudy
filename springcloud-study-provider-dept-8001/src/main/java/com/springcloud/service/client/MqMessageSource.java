package com.springcloud.service.client;

import com.springcloud.entity.Company;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author han_lic
 * @date 2021/1/18 14:51
 */
public interface MqMessageSource {
    /**
     * 实现消息的发送，本次发送的消息是一个对象（自动变为json）
     * @param company VO对象，该对象不为null*/
    public void send(Company company) ;
}
