package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result =  paymentService.paymentInfo_OK(id);
        log.info("**********result:{}",result);
        return result;
    }
    @GetMapping("payment/hystrix/timeout/ok/{id}")
    public String paymentInfo_TimeOut_OK(@PathVariable("id") Integer id){
        String result =  paymentService.paymentInfo_Timeout(id);
        log.info("**********timeout result:{}",result);
        return result;
    }
}
