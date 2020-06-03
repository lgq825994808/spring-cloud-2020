package com.adb.springcloud.controller;

import com.adb.springcloud.entities.CommonResult;
import com.adb.springcloud.service.OrderFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private OrderFeignService orderFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
       return orderFeignService.getPaymentById(id);
    }

    /**
     * 测试ribbon的超时限制
     * @return
     */
    @GetMapping(value = "/consumer/payment/timeout")
    public String getPaymentTimeout(){
        return orderFeignService.getPaymentTimeout();
    }
}
