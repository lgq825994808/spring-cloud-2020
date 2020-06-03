package com.adb.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 统一的服务降级处理方法类
 */
@Component
public class OrderFallbackService implements OrderFeignHystrixService{
    @Override
    public String paymentOK(Integer id) {
        return "方法:paymentOK  进行了降级处理";
    }

    @Override
    public String paymentTimeOut(Integer id) {
        return "方法:paymentTimeOut  进行了降级处理";
    }
}

