package com.wuqi.facepay.bean;

public class Order {
    private double price = 0;
    private int payStyle = 1; // 支付方式：1微信 2支付宝 3聚合

    public Order(double price, int payStyle){
        this.price = price;
        this.payStyle = payStyle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPayStyle() {
        return payStyle;
    }

    public void setPayStyle(int payStyle) {
        this.payStyle = payStyle;
    }
}
