package com.wuqi.facepay.presenter;

import android.content.Context;
import android.util.Log;

import com.wuqi.facepay.R;
import com.wuqi.facepay.bean.ApiToken;
import com.wuqi.facepay.bean.JsonResponse;
import com.wuqi.facepay.bean.User;
import com.wuqi.facepay.contract.LoginContract;
import com.wuqi.facepay.model.LoginModel;
import com.wuqi.facepay.util.FacePay;
import com.wuqi.facepay.util.JsonHelper;
import com.wuqi.facepay.util.SharedPrefeerenceUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.wuqi.facepay.activity.PayActivity.TAG;

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
        this.loginModel = new LoginModel();
    }

    @Override
    public void login(User user) {
        Log.d("LoginPresenter", String.valueOf(user.getAccoount().equals("1586714785")));
        Log.d("LoginPresenter", String.valueOf(user.getPassword().equals("123456")));
        loginView.showLoading();
        if (!isUserNameValid(user.getAccoount())) {
            loginView.showFailedToast(R.string.invalid_username);
        } else if (!isPasswordValid(user.getPassword())) {
            loginView.showFailedToast(R.string.invalid_password);
        } else {
            loginModel.login(user, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, "onFailure: " + e.getMessage());
                    loginView.showSuccessToast(R.string.login_failed);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()){
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        }
                    }

                    try {
                        String body = response.body().string();
                        JsonResponse jsonResponse = JsonHelper.parseObject(body, JsonResponse.class);
                        Log.d(TAG, "onResponse: "+ body);
                        Log.d(TAG, "jsonResponse: status=" + jsonResponse.getStatus());
                        Log.d(TAG, "jsonResponse: msg=" + jsonResponse.getMsg());
                        Log.d(TAG, "jsonResponse: data=" + jsonResponse.getData());
                        if (jsonResponse.isSuccess()){
                            ApiToken apiToken = JsonHelper.parseObject(jsonResponse.getData(), ApiToken.class);
                            Log.d(TAG, "jsonResponse: userToken=" + apiToken.getUserToken());
                            SharedPrefeerenceUtil.putString((Context) loginView, "userToken", apiToken.getUserToken());
                            loginView.showSuccessToast(R.string.login_success);
                        }else{
                            loginView.showFailedToast(R.string.login_failed);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
//            loginView.showSuccessToast(R.string.login_success);
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
