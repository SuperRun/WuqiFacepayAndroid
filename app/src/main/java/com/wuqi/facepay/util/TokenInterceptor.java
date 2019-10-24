package com.wuqi.facepay.util;

import android.util.Log;
import android.view.animation.Interpolator;

import com.wuqi.facepay.activity.CarouselActivity;
import com.wuqi.facepay.activity.FaceApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ClassName TokenInterceptor
 * @Description token拦截器
 * @Author Luo Yi
 * @Date 2019/10/22 13:44
 */
public class TokenInterceptor implements Interceptor {
    private String token; //用于添加的请求头

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        //从SharePreferences中获取token
        token = SharedPrefeerenceUtil.getString(FaceApplication.instance,"userToken");

        Log.d("TokenInterceptor", "token=" + token);

        Request request = chain.request()
                .newBuilder()
                .addHeader("x-access-token", token)
                .build();
        Response response = chain.proceed(request);
        return response;
    }

}
