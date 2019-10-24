package com.wuqi.facepay.bean;

public enum ResponseCode {
    USER_DISABLE((short)10301 , "用户已被禁用"),
    USER_UNEXIST((short)10302  , "用户不存在");

    private short code;


    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    ResponseCode(short code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
