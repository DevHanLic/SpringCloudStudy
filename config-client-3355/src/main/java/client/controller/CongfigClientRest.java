package client.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HLC
 */
@RestController
public class CongfigClientRest {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServers;
    @Value("${server.port}")
    private String port;
    @Value("${spring.datasource.password}")
    private String strValue;
    @Value("${mybatis.mapper-locations}")
    private String strValue1;
    @Value("${spring.cloud.stream.binders.defaultRabbit.environment.spring.rabbitmq.password}")
    private String Password;
  @RequestMapping("/config")
  public String getCongfig(){
      String str ="applicationName\n"+applicationName+"eurekaServers\n"+eurekaServers+"port\n"+port+"str\n"+strValue+strValue1;
      System.out.println(str);
      System.out.println(Password);
      return  str;
  }
}
