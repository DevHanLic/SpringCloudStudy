package com.springcloud.controller;

import com.springcloud.entity.DeptEntity;
import com.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptConsumerController {

    @Autowired
    private DeptClientService deptClientService;//Feign面向接口编程

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(DeptEntity deptEntity) {
        //三个参数：url,requestMap ResponseBean.class
        return deptClientService.add(deptEntity);
    }

    @RequestMapping("/consumer/dept/findById/{deptNo}")
    public DeptEntity findById(@PathVariable("deptNo") Long deptNo) {
        //三个参数：url,requestMap ResponseBean.class
        return deptClientService.findById(deptNo);
    }

    @RequestMapping(value = "/consumer/dept/findAll")
    public List findAll() {
        //三个参数：url,requestMap ResponseBean.class
        return deptClientService.findAll();
    }

    @EnableBinding(Sink.class)
    @Service
    public class LianxiCuseromer {

        @StreamListener(Sink.INPUT)
        public void test(Message<String> message) {
            String payload = message.getPayload();
            System.out.println(payload);
        }
    }
}
