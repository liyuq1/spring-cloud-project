package com.atguigu.springcloud.service.api;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * (Payment)表控制层
 *
 * @author liyu
 * @since 2020-11-03 10:10:06
 */
@Component
@FeignClient(name = "cloud-payment-service")
public interface PaymentController {

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("get/{id}")
    CommonResult selectOne(@PathVariable("id") Long id) ;


    @PostMapping("create")
    CommonResult add(@RequestBody Payment payment) ;

}