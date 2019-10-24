package com.wuqi.facepay.presenter;

import android.util.Log;

import com.wuqi.facepay.bean.AdImg;
import com.wuqi.facepay.bean.JsonResponse;
import com.wuqi.facepay.contract.LogoutContract;
import com.wuqi.facepay.model.LogoutModel;
import com.wuqi.facepay.util.JsonHelper;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.wuqi.facepay.activity.PayActivity.TAG;

/**
 * @ClassName LogoutPresenter
 * @Description
 * @Author Luo Yi
 * @Date 2019/10/22 18:05
 */
public class LogoutPresenter implements LogoutContract.Presenter {

    private LogoutContract.View logoutView = null;
    private LogoutModel logoutModel = null;

    public LogoutPresenter(LogoutContract.View logoutView){
        this.logoutView = logoutView;
        this.logoutModel = new LogoutModel();
    }

    @Override
    public void logout(String password) {
        logoutModel.logout(password, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("LogoutPresenter", "onFailure: " + e.getMessage());
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
                    Log.d("LogoutPresenter", "jsonResponse: body=" + body);
                    Log.d("LogoutPresenter", "jsonResponse: status=" + jsonResponse.getStatus());
                    Log.d("LogoutPresenter", "jsonResponse: msg=" + jsonResponse.getMsg());
                    if (jsonResponse.isSuccess()){
                        Log.d("LogoutPresenter", "onResponse:退出成功");
                        logoutView.showSuccessToast();
                    }else if (!jsonResponse.ifTokenValid()){
                        Log.d("LogoutPresenter", "onResponse:请先登录");
                        logoutView.showFailedToast("请先登录");
                        logoutView.goToLoginPage();
                    }else{
                        Log.d("LogoutPresenter", "onResponse: 密码不正确");
                        logoutView.showFailedToast("密码不正确");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
