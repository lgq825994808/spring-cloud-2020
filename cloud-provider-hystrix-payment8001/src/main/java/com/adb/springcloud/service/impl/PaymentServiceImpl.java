package com.adb.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.adb.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentOK(Integer id) {
        //int age = 10/0;
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id："+id+"\t"+"O(∩_∩)O哈哈~";
    }


    /**
     * 服务端自己开启了服务降级处理，保证服务端的资源不被长期占用
     * 1：配置了超时时间后，如果方法的返回时间大于配置的时间，就会进入fallbackMethod对应的回调方法
     * 2：如果该方法本身报错了，也会进入回调方法
     *
     *feign调用该服务接口时，默认一秒就会超时
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentTimeOutHandler",commandProperties = {
            //设置这个线程的超时时间是5s，5s内是正常的业务逻辑，超过5s调用fallbackMethod指定的方法进行处理
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    @Override
    public String paymentTimeOut(Integer id) {
        int timeNumber = 3000;
        //int age = 10/0;
        try{
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_Timeout,id："+id+"\t"+"O(∩_∩)O哈哈~"+"   耗时(秒)："+timeNumber;
    }

    public String paymentTimeOutHandler(Integer id) {
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_Timeout,  服务降级处理";
    }


    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),   //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),  //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),    //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),    //失败率达到多少后跳闸
    })
    @Override
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("******id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();  //UUID.randomUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }
    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍后再试，服务进行了降级处理  id："+id+"  ,"+IdUtil.simpleUUID();
    }

}
