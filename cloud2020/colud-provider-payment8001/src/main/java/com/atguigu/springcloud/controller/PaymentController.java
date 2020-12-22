package com.atguigu.springcloud.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * (Payment)表控制层
 *
 * @author liyu
 * @since 2020-11-03 10:10:06
 */
@RestController
@Slf4j
public class PaymentController {
    /**
     * 服务对象
     */
    @Value("${server.port}")
    private String serverPort;

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
        log.info("{}***查询数据id：{}",serverPort,id);
        log.info("{}***查询数据id：{}",serverPort,id+"aaaaaaaaaaaaaaa");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Payment payment = this.paymentService.queryById(id);
        return new CommonResult(serverPort,200,"SUCCESS",payment);
    }


    @PostMapping("create")
    public CommonResult add(@RequestBody Payment payment) {
        int a = this.paymentService.insert(payment);
        log.info("***插入数据：{}",payment);
        if(a>0)return new CommonResult(serverPort,200,"SUCCESS",payment);
        return new CommonResult(serverPort,444,"FAIL",payment);
    }
    @PostMapping("login")
    public Map login(@RequestBody Map<String,String> input){
        //拼接url
        StringBuilder url = new StringBuilder("http://127.0.0.1:8081/sns/jscode2session?");
        url.append("appid=");//appid设置
        url.append("wxe043dce650eac1de");
        url.append("&secret=");//secret设置
        url.append("c5578fa595a45c8d638dd76b4d4119b5");
        url.append("&js_code=");//code设置
        log.info("code:{}",input.get("code"));
        url.append(input.get("code"));
        url.append("&grant_type=authorization_code");
        Map map = new HashMap();
        try {
            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
            HttpGet get = new HttpGet(url.toString());    //构建一个GET请求
            HttpResponse response = client.execute(get);//提交GET请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
            String content = EntityUtils.toString(result);
            System.out.println(content);//打印返回的信息
            JSONObject res = JSON.parseObject(content);//把信息封装为json
            String errcode = res.getString("errcode");
            if(StringUtils.isEmpty(errcode)){
                //微信登陆报错
                //登陆失败处理
            }
            String openid = res.getString("openid");
            String session_key = res.getString("session_key");
            String unionid = res.getString("unionid");
            map.put("openid",openid);
            map.put("session_key",session_key);
            map.put("unionid",unionid);
            //把信息封装到map
        } catch (Exception e) {
            //异常处理
            e.printStackTrace();
        }
        return map;
    }


    @RequestMapping("sns/jscode2session")
    public CommonResult add(Map map) {
        log.info(JSONObject.toJSONString(map));
        return new CommonResult(200,"SUCCESS",null);

    }
}