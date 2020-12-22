package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-PAYMENT")
//@FeignClient(url = "http://localhost:8001",name="order")
public interface PaymentHystrixService {

    @GetMapping("payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("payment/hystrix/timeout/ok/{id}")
    String paymentInfo_TimeOut_OK(@PathVariable("id") Integer id);
}
