package com.atguigu.springcloud.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * (Payment)实体类
 *
 * @author liyu
 * @since 2020-11-03 10:04:44
 */

@Data
@Builder
public class Payment {

    @Tolerate
    public Payment() {
    }
    private Long id;
    private String serial;
}