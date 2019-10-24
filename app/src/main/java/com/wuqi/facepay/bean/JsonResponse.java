package com.wuqi.facepay.bean;

/**
 * json 响应
 *
 * @author pojun
 */
public class JsonResponse<T> {


    // 响应码。0：失败，1：成功，2：token失效。
    private Integer status;

    // 错误描述
    private String msg;

    // 响应数据
    private String data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSuccess(){
        if (this.status == Constant.DEFAULT_SUCCESS_CODE){
            return true;
        }
        return false;
    }

    public boolean ifTokenValid(){
        if (this.status == Constant.TOKEN_INVALIDATE){
            return false;
        }
        return true;
    }

}
