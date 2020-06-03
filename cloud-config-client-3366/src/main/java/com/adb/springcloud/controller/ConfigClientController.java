package com.adb.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //用于实现配置文件的动态刷新
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;  //要访问的3344上的信息

    //在git上修改了配置以后，配置中心本身（配置中心服务端）可以实现配置文件的动态刷新
    //但是，获取配置中心具体配置文件的微服务（配置中心客服端）不能实现配置文件的动态刷新
    //需要手动执行  curl -X POST "http://localhost:3366/actuator/refresh"  该条命令来刷新配置，此时就不用重启微服务也能实现配置文件的动态刷新

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}
