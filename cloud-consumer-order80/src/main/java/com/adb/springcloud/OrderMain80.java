package com.adb.springcloud;

import com.adb.myrule.MyRule;
import com.adb.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//开启ribbon，并指定负载均衡算法类，如果不指定负载均衡算法类，就会采用默认的轮询均衡
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyRule.class)
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE")
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
