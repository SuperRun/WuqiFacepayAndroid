package com.wuqi.facepay.config;

public class APIConfig {

    public static String API_BASE_URL = "http://api.sh.wuqi365.com/app/v1";

    public static String BASE_URL = "http://api.sh.wuqi365.com";

    // 登录
    public static String TOKEN_URL = API_BASE_URL + "/login";
    // 获取广告
    public static String AD_URL = API_BASE_URL + "/ad";
    // 退出
    public static String LOGOUT_URL = API_BASE_URL + "/loginOut";
    // 生成订单
    public static String ORDER_URL = API_BASE_URL + "/order";

}
