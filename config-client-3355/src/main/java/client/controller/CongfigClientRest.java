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

  @RequestMapping("/config")
  public String getCongfig(){
      String str ="applicationName"+ applicationName+"eurekaServers"+eurekaServers+"port"+port;
      System.out.println(str);
      return  str;
  }
}
