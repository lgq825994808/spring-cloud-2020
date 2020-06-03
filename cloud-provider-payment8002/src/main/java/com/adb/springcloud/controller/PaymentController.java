package com.adb.springcloud.controller;

import com.adb.springcloud.entities.CommonResult;
import com.adb.springcloud.entities.Payment;
import com.adb.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String servicePort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("********插入结果:{}",result);
        if(result>0){
            return new CommonResult(200,"插入payment成功,servicePort:"+servicePort,result);
        }else{
            return new CommonResult(444,"插入payment失败",null);
        }
    };

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("********查询结果:{}",payment);
        if(payment!=null){
            return new CommonResult(200,"查询Payment成功,servicePort:"+servicePort,payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询id为"+id,null);
        }
    };

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return servicePort;
    }

    @GetMapping(value = "/payment/timeout")
    public String getPaymentTimeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return servicePort;
    }

    /**
     * 用于调试展示zipkin
     * @return
     */
    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "hi, i'am paymentzipkin server fall back,welcome to atguigu, O(∩_∩)O哈哈~";
    }
}
