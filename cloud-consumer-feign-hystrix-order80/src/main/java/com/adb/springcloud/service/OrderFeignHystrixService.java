package com.adb.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE",fallback = OrderFallbackService.class)
public interface OrderFeignHystrixService {

    /**
     * feign调用的时候，默认的超时时间是1秒
     *
     */

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentOK(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentTimeOut(@PathVariable("id") Integer id);
}
