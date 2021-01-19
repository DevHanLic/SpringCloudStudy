package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.springcloud.entity.Company;
import com.springcloud.entity.DeptEntity;
import com.springcloud.service.DeptService;


import com.springcloud.service.client.Impl.MqMessageProducer;
import com.springcloud.service.client.MqMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private MqMessageSource mqMessageSource;

    @RequestMapping(value = "/dept/discovery",method = RequestMethod.GET)
    public Object discovery(){
        List<String> list = discoveryClient.getServices();
        List<ServiceInstance> instances = discoveryClient.getInstances("STUDY-SPRINGCLOUD-DEPT");

        for (ServiceInstance element :instances){
            System.out.println(element.getServiceId());
            System.out.println(element.getHost());
            System.out.println(element.getPort());
            System.out.println(element.getUri());
        }
        return this.discoveryClient;

    }
    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public boolean addDept(@RequestBody DeptEntity deptEntity) {
        return   deptService.addDept(deptEntity);
    }

    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    @RequestMapping(value = "/dept/findById/{deptNo}",method = RequestMethod.GET)
    public DeptEntity findById(@PathVariable("deptNo") Long deptNo) {
        DeptEntity dept = this.deptService.findById(deptNo);
        if(dept == null){
            throw new RuntimeException("该Id：" + deptNo + "没有对应的信息");
        }
        return dept;
    }
    public DeptEntity processHystrix_Get(@PathVariable("deptNo") Long deptNo) {
        return new DeptEntity().setDeptNo(deptNo).setDeptName("该Id"+ deptNo +"没有对应的信息,null -- @HystrixCommand")
                .setDbSource("no this database in MySql");
    }

    @RequestMapping(value = "/dept/findAll",method = RequestMethod.GET)
    public List<DeptEntity> findAll() {
        return deptService.findAll();
    }

    @GetMapping(value = "/testMq")
    public String testMq(){
        Company company = new Company();
        company.setTitle("studyjava");
        company.setNote("更多资源请登录：www.study.cn");
        mqMessageSource.send(company);
        return "发送成功";
    }


    @Value("${spring.cloud.stream.binders.defaultRabbit.environment.spring.rabbitmq.host}")
    private String Host;
    @Value("${spring.cloud.stream.binders.defaultRabbit.environment.spring.rabbitmq.username}")
    private String Username;
    @Value("${spring.cloud.stream.binders.defaultRabbit.environment.spring.rabbitmq.password}")
    private String Password;
    @Value("${spring.cloud.stream.binders.defaultRabbit.environment.spring.rabbitmq.port}")
    private int Port;
    @Value("${spring.cloud.stream.bindings.output.binder}")
    private String name;
    @Value("${spring.datasource.password}")
    private String strValue;
    @GetMapping(value = "/testMqs")
    public String testMqs() throws IOException, TimeoutException {
        System.out.println(strValue);
        System.out.println(Password);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Host);
        factory.setUsername(Username);
        factory.setPassword(Password);
        factory.setPort(Port);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(name, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", name, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
        return "发送成功";
    }
//    @Value("${spring.datasource.password}")
//    private String strValue;
//    @Value("${mybatis.mapper-locations}")
//    private String strValue1;
//    @Value("${mybatis.type-aliases-package}")
//    private String strValue2;
//    @Value("${spring.cloud.stream.binders.test.type}")
//    private String strValue2;
//    @RequestMapping("/config")
//    public String getCongfig(){
//        String str = strValue2;
//        System.out.println(str);
//        return  str;
//    }
}
