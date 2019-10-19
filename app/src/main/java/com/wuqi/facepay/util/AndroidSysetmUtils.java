package com.wuqi.facepay.util;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;

public class AndroidSysetmUtils {

    public static String getIpAddress(Context ctx){
        WifiManager wifiManager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        int ip = dhcpInfo.serverAddress;
        //此处获取ip为整数类型，需要进行转换
        return intToIp(ip);
    }

    private static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "."
                + ((i >> 24) & 0xFF);
    }
}
