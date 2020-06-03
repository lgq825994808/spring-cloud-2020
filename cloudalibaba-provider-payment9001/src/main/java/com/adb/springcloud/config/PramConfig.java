package com.adb.springcloud.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@RefreshScope  //SpringCloud原生注解 支持Nacos的动态刷新功能
public class PramConfig {

    @Value("${config.info}")
    private String configInfo;
}
