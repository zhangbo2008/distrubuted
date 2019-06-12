package com.hc.hccommon.base;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一返回类
 */
@Data
@AllArgsConstructor
public class BaseResult {

    /**
     * 状态码：1成功，其他为失败
     */
    public int code;

    /**
     * 成功为success，其他为失败原因
     */
    public String message;

    /**
     * 数据结果集
     */
    public Object data;
}
