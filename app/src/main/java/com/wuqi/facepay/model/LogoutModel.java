package com.wuqi.facepay.model;

import android.util.Log;

import com.wuqi.facepay.config.APIConfig;
import com.wuqi.facepay.contract.LogoutContract;
import com.wuqi.facepay.util.OkHttpUtils;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @ClassName LogoutModel
 * @Description
 * @Author Luo Yi
 * @Date 2019/10/22 18:05
 */
public class LogoutModel implements LogoutContract.Model {
    @Override
    public void logout(String password, Callback callback) {
        Log.d("LogoutModel", "logout: ");
        OkHttpClient okHttpClient = OkHttpUtils.getOkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("password",password)
                .build();
        Request request = new Request.Builder()
                .url(APIConfig.LOGOUT_URL)
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
