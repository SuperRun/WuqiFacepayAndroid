package com.wuqi.facepay.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @ClassName OkHttpsUtil
 * @Description http工具类
 * @Author Luo Yi
 * @Date 2019/10/22 13:43
 */
public class OkHttpUtils {

    /**
     * 获取okhttpclient
     * @return
     */
    public static OkHttpClient getOkHttpClient() {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(3000, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(3000, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(3000, TimeUnit.SECONDS);
        //使用拦截器
        httpClientBuilder.addInterceptor(new TokenInterceptor());
        return httpClientBuilder.build();
    }
}
