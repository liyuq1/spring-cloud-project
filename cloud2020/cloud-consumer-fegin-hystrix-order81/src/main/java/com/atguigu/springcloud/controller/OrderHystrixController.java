package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystrixController {

    @Resource
    private  PaymentHystrixService paymentHystrixService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result =  paymentHystrixService.paymentInfo_OK(id);
        log.info("{}:**********result:{}",serverPort,result);
        return result;
    }
    @GetMapping("consumer/payment/hystrix/timeout/ok/{id}")
    public String paymentInfo_TimeOut_OK(@PathVariable("id") Integer id){
        String result =  paymentHystrixService.paymentInfo_TimeOut_OK(id);
        log.info("{},**********timeout result:{}",serverPort,result);
        return result;
    }
}
