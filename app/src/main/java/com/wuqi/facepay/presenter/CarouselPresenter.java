package com.wuqi.facepay.presenter;

import android.content.Context;
import android.util.Log;

import com.wuqi.facepay.R;
import com.wuqi.facepay.bean.AdImg;
import com.wuqi.facepay.bean.ApiToken;
import com.wuqi.facepay.bean.JsonResponse;
import com.wuqi.facepay.bean.Order;
import com.wuqi.facepay.contract.CarouselContract;
import com.wuqi.facepay.contract.LoginContract;
import com.wuqi.facepay.model.CarouselModel;
import com.wuqi.facepay.model.LoginModel;
import com.wuqi.facepay.util.JsonHelper;
import com.wuqi.facepay.util.SharedPrefeerenceUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.wuqi.facepay.activity.PayActivity.TAG;

/**
 * @ClassName CarouselPresenter
 * @Description
 * @Author Luo Yi
 * @Date 2019/10/22 15:41
 */
public class CarouselPresenter implements CarouselContract.Presenter {
    CarouselContract.View carouselView = null;
    CarouselModel carouselModel = null;
    public AdImg[] ads = null;

    public CarouselPresenter(CarouselContract.View carouselView) {
        this.carouselView = carouselView;
        this.carouselModel = new CarouselModel();
    }

    @Override
    public void getAds() {
        carouselModel.getAds(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()){
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }
                }

                try {
                    JsonResponse jsonResponse = JsonHelper.parseObject(response.body().string(), JsonResponse.class);
                    Log.d(TAG, "jsonResponse: status=" + jsonResponse.getStatus());
                    Log.d(TAG, "jsonResponse: msg=" + jsonResponse.getMsg());
                    if (jsonResponse.isSuccess()){
                        ads = JsonHelper.parseArray(jsonResponse.getData(), AdImg.class);
                        carouselView.createAds(ads);
                        Log.d(TAG, "jsonResponse: ads length=" + ads[0].getPath());
                    }else{
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void createOrder(Order order) {
        carouselModel.createOrder(order, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("PayPresenter", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()){
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }
                }

                try {
                    JsonResponse jsonResponse = JsonHelper.parseObject(response.body().string(), JsonResponse.class);
                    Log.d(TAG, "jsonResponse: status=" + jsonResponse.getStatus());
                    Log.d(TAG, "jsonResponse: msg=" + jsonResponse.getMsg());
                    if (jsonResponse.isSuccess()){
                        // 生成订单成功
                        carouselView.goToPayActivity();
                    }else if (!jsonResponse.ifTokenValid()){
                        carouselView.goToLoginActivity();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
