package com.irisking.bean;


public class Response<T> {
    private Integer code = 0;
    private String msg = "OK";
    private T payload;
    
    public void setCode(Integer code) {
        this.code = code;
    } 
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public void setPayload(T load) {
        this.payload = load;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getPayload() {
        return payload;
    }
}
