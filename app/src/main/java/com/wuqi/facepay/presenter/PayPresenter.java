package com.wuqi.facepay.presenter;

import android.util.Log;

import androidx.viewpager.widget.PagerAdapter;

import com.wuqi.facepay.bean.JsonResponse;
import com.wuqi.facepay.bean.Order;
import com.wuqi.facepay.contract.LoginContract;
import com.wuqi.facepay.contract.PayContract;
import com.wuqi.facepay.model.LoginModel;
import com.wuqi.facepay.model.PayModel;
import com.wuqi.facepay.util.JsonHelper;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.wuqi.facepay.activity.PayActivity.TAG;

public class PayPresenter implements PayContract.Presenter {
    private PayContract.View payView = null;
    private PayModel payModel= null;

    public PayPresenter(PayContract.View payView){
        this.payView = payView;
        this.payModel = new PayModel();
    }
    @Override
    public void createOrder(Order order) {
        payModel.createOrder(order, new Callback() {
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
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
