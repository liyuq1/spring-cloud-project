package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Payment)表控制层
 *
 * @author liyu
 * @since 2020-11-03 10:10:06
 */
@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {
    /**
     * 服务对象
     */
    @Resource
    private PaymentService paymentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("get/{id}")
    public CommonResult selectOne(@PathVariable("id") Long id) {
        log.info("***查询数据id：{}",id);
        log.info("***查询数据id：{}",id+"aaaaaaaaaaaaaaa");
        Payment payment = this.paymentService.queryById(id);
        return new CommonResult(200,"SUCCESS",payment);
    }


    @PostMapping("create")
    public CommonResult add(@RequestBody Payment payment) {
        int a = this.paymentService.insert(payment);
        log.info("***插入数据：{}",payment);
        if(a>0)return new CommonResult(200,"SUCCESS",payment);
        return new CommonResult(444,"FAIL",payment);
    }
}