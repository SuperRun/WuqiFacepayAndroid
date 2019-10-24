package com.wuqi.facepay.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wuqi.facepay.R;

public class ToastUtil {

    private static Toast toast;

    public static void showToast(Context context, String msg, int duration){
        if (toast ==null) toast = new Toast(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.toast_layout, null);
        TextView toast_msg = view.findViewById(R.id.toastMsg);
        toast_msg.setText(msg);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
