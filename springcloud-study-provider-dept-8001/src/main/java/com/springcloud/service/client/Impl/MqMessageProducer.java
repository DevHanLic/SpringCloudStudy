package com.springcloud.service.client.Impl;

import com.springcloud.entity.Company;
import com.springcloud.service.client.MqMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

/**
 * @author han_lic
 * @date 2021/1/18 14:52
 */
@EnableBinding(Source.class)
public class MqMessageProducer implements MqMessageSource{
    @Resource
    // 消息的发送管道
    private MessageChannel output;

    @Override
    public void send(Company company) {
        // 创建并发送消息
       output.send(MessageBuilder.withPayload(company).build());
    }
}
