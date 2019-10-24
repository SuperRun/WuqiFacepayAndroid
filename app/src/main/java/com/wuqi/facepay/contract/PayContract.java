package com.wuqi.facepay.contract;

import com.wuqi.facepay.bean.Order;

import okhttp3.Callback;


public interface PayContract {
    interface Model {
        void createOrder(Order order, Callback callback);
    }

    interface View {
    }

    interface Presenter {
        void createOrder(Order order);
    }
}
