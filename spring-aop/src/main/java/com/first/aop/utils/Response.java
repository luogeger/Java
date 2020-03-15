package com.first.aop.utils;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 *  统一API响应结果封装
 */
public class Response {
    private Integer status;
    private String code;
    private String msg;
    private Object data;
    private Map<String, String> extra;

    public Response(){
        this.status = SystemStatus.SUCCESS.getStatus();
        this.code = SystemStatus.SUCCESS.getCode();
        this.msg = SystemStatus.SUCCESS.getMessage();
    }

    public Response(Object data) {
        this.status = SystemStatus.SUCCESS.getStatus();
        this.code = SystemStatus.SUCCESS.getCode();
        this.msg = SystemStatus.SUCCESS.getMessage();
        this.data = data;
    }

    public Response setCode(SystemStatus systemStatus) {
        this.code = systemStatus.getCode();
        this.msg = systemStatus.getMessage();
        return this;
    }

    public String getCode() {
        return code;
    }

    public Response setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        if (null == code) {
            setCode(SystemStatus.SUCCESS.getCode());
        }
        if (null == data) {
            setData("");
        }
        return JSON.toJSONString(this);
    }
}
