package com.wuqi.facepay.model;

import android.util.Log;

import com.wuqi.facepay.R;
import com.wuqi.facepay.bean.User;
import com.wuqi.facepay.contract.LoginContract;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName LoginModel
 * @Description
 * @Author Luo Yi
 * @Date 2019/10/21 16:19
 */
public class LoginModel implements LoginContract.Model {

    @Override
    public int login(User user) {
        return 0;
    }


}
