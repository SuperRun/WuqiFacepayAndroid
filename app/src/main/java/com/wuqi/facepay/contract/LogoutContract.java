package com.wuqi.facepay.contract;

import okhttp3.Callback;

public interface LogoutContract {
    interface Model {
        void logout(String password, Callback callback);
    }

    interface View {
        void showSuccessToast();
        void showFailedToast(String msg);
        void showFailedText(String msg);
        void goToLoginPage();
        void showLoading();
    }

    interface Presenter {
        void logout(String password);
    }
}
