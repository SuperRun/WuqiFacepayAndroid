package com.wuqi.facepay.model;

import android.util.Log;

import com.wuqi.facepay.bean.Order;
import com.wuqi.facepay.config.APIConfig;
import com.wuqi.facepay.contract.PayContract;
import com.wuqi.facepay.util.OkHttpUtils;


import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PayModel implements PayContract.Model {
    @Override
    public void createOrder(Order order, Callback callback) {
        Log.d("PayModel", "createOrder: price="+order.getPrice());
        OkHttpClient okHttpClient = OkHttpUtils.getOkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("pay_style", String.valueOf(order.getPayStyle()))
                .add("price", String.valueOf(order.getPrice()))
                .build();
        Request request = new Request.Builder()
                .url(APIConfig.ORDER_URL)
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
