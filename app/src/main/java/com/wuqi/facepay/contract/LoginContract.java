package com.wuqi.facepay.contract;

import com.wuqi.facepay.bean.User;

/**
 * @ClassName LoginPresenter
 * @Description
 * @Author Luo Yi
 * @Date 2019/10/21 16:19
 */
public interface LoginContract {
    interface Model {
        int login(User user);
    }

    interface View {
        void showSuccessToast(int msgRes);
        void showFailedToast(int msgRes);
        void showLoading();
    }

    interface Presenter {
        void login(User user);
    }
}
