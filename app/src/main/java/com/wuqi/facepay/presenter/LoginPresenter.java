package com.wuqi.facepay.presenter;

import android.content.Context;
import android.util.Log;

import com.wuqi.facepay.R;
import com.wuqi.facepay.bean.User;
import com.wuqi.facepay.contract.LoginContract;
import com.wuqi.facepay.model.LoginModel;
import com.wuqi.facepay.util.FacePay;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName LoginPresenter
 * @Description
 * @Author Luo Yi
 * @Date 2019/10/21 16:19
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView = null;
    private LoginModel loginModel= null;

    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
        this. loginModel = new LoginModel();
    }

    @Override
    public void login(User user) {
        Log.d("LoginPresenter", String.valueOf(user.getUserName().equals("1586714785")));
        Log.d("LoginPresenter", String.valueOf(user.getPassword().equals("123456")));
        loginView.showLoading();
        if (!isUserNameValid(user.getUserName())) {
            loginView.showFailedToast(R.string.invalid_username);
        } else if (!isPasswordValid(user.getPassword())) {
            loginView.showFailedToast(R.string.invalid_password);
        } else if (user.getUserName().equals("15868174785") && user.getPassword().equals("123456")){
            // 初始化微信刷脸sdk
            FacePay.initPayFace((Context) loginView);
            loginView.showSuccessToast(R.string.login_success);
        }else{
            loginView.showFailedToast(R.string.login_failed);
        }
    }

    /**
     * 验证用户名是否有效
     * @param username
     * @return
     */
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(username);
        return m.matches();
    }


    /**
     * 验证密码是否有效
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
