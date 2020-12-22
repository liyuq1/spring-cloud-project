package com.atguigu.springcloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_OK,id:"+id+"\t"+"(●'◡'●)哈哈~";
    }

    public String paymentInfo_Timeout(Integer id){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        int timeNumber = 3;
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_OK,id:"+id+"\t"+"(●'◡'●)哈哈~"
                +"  耗时"+timeNumber+"秒钟";
    }
}
