package com.atguigu.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private String port;
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    public CommonResult(Integer code, String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
