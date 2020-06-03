package com.adb.springcloud.service;

public interface PaymentService {

    public String paymentOK(Integer id);

    public String paymentTimeOut(Integer id);

    String paymentCircuitBreaker(Integer id);
}
