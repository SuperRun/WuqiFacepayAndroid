package com.wuqi.facepay.bean;

import android.text.Editable;

/**
 * @ClassName User
 * @Description User 实体类
 * @Author Luo Yi
 * @Date 2019/10/21 16:04
 */
public class User {
    private String userName;

    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
