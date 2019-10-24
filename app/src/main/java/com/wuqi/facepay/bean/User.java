package com.wuqi.facepay.bean;

import android.text.Editable;

/**
 * @ClassName User
 * @Description User 实体类
 * @Author Luo Yi
 * @Date 2019/10/21 16:04
 */
public class User {
    private String account;

    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public User(String userName, String password) {
        this.account = userName;
        this.password = password;
    }

    public String getAccoount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
