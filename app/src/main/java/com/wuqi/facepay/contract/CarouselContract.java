package com.wuqi.facepay.contract;


import com.wuqi.facepay.bean.AdImg;
import com.wuqi.facepay.bean.Order;

import okhttp3.Callback;

public interface CarouselContract {
    interface Model{
        void getAds(Callback callback);
        void createOrder(Order order, Callback callback);
    }

    interface View{
        void createAds(AdImg[] adImgs);
        void goToPayActivity();
        void goToLoginActivity();
    }

    interface Presenter {
        void getAds();
        void createOrder(Order order);
    }
}
