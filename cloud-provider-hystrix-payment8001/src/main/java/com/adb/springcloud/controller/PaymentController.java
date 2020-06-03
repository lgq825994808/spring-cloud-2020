package com.adb.springcloud.controller;

import com.adb.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentOK(@PathVariable("id") Integer id){
        String result = paymentService.paymentOK(id);
        log.info("*****result："+result);
        return result;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentTimeOut(@PathVariable("id") Integer id){
        String result = paymentService.paymentTimeOut(id);
        log.info("*****result："+result);
        return result;
    }



    //********************服务熔断*********************************
    //当服务的失败率达到了该方法的设置值时，该方法会触发熔断，会在一定(默认5秒)时间内给所有的请求进行降级处理，
    //过一会后(默认5秒)，然后请求会尝试的调用该服务，如果发现服务能正常返回后，就恢复该服务链路的调用
    //该方法设置的失败率在service层
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("****result："+result);
        return result;
    }
}
