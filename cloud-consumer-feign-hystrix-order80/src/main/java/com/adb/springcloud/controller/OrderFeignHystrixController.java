package com.adb.springcloud.controller;

import com.adb.springcloud.service.OrderFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//该注解配置了全局的降级处理方法，就是所有的业务方法都用该降级方法
//同时需要给全部的每个方法添加@HystrixCommand注解
//同时需要每个方法的返回值一样，这样才能保证所有业务方法和全局降级处理方法的返回值一样，不然就会报错
//@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderFeignHystrixController {

    @Autowired
    private OrderFeignHystrixService orderFeignHystrixService;

    @GetMapping(value = "/consumer/hystrix/ok/{id}")
    //@HystrixCommand
    public String paymentOK(@PathVariable("id") Integer id){
        String result = orderFeignHystrixService.paymentOK(id);
        return result;
    }

    /**
     *单独开启一个自己方法的服务降级处理，调用方和服务方都可以开启
     *
     *该方法的问题是：需要为每个业务方法提供一个降级处理的回调方法（fallbackMethod）
     *                  这样处理的业务代码与降级处理方法严重耦合，处理方法不够优雅
     *  解决方法是：在feign的接口方法里面统一实现
     *
     * 调用方自己开启了服务降级处理，保证自己获取不到别的服务返回的结果时，自己进行降级处理
     * 1：配置了超时时间后，如果方法的返回时间大于配置的时间，就会进入fallbackMethod对应的回调方法
     * 2：如果该方法本身报错了，也会进入回调方法
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentTimeOutHandler",commandProperties = {
            //设置这个线程的超时时间是3s，3s内是正常的业务逻辑，超过3s调用fallbackMethod指定的方法进行处理
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })*/
    //@HystrixCommand
    public String paymentTimeOut(@PathVariable("id") Integer id){
        String result = orderFeignHystrixService.paymentTimeOut(id);
        return result;
    }
    public String paymentTimeOutHandler(Integer id) {
        return "线程池："+Thread.currentThread().getName()+"  支付服务不可用，或者自身的订单服务报错";
    }

    /**
     * 全局 fallback 方法
     * @return
     */
    public String paymentGlobalFallbackMethod(){
        return "Global异常处理信息，请稍后再试。/(╥﹏╥)/~~";
    }
}
