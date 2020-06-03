package com.adb.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancerService {

    //获取ribbone 当前使用自定义算法时，调用服务的地址
    ServiceInstance instances(List<ServiceInstance> serviceInstanceList);
}
