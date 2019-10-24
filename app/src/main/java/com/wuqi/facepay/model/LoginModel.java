package com.wuqi.facepay.model;

import com.wuqi.facepay.bean.User;
import com.wuqi.facepay.config.APIConfig;
import com.wuqi.facepay.contract.LoginContract;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @ClassName LoginModel
 * @Description
 * @Author Luo Yi
 * @Date 2019/10/21 16:19
 */
public class LoginModel implements LoginContract.Model {
    String TAG = "LoginModel";

    @Override
    public void login(User user, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("account",user.getAccoount())
                .add("password",user.getPassword())
                .build();
        Request request = new Request.Builder()
                .url(APIConfig.TOKEN_URL)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
