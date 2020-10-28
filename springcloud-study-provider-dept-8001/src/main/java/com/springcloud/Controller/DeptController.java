package com.springcloud.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.entities.DeptEntity;
import com.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient discoveryClient;

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


}
