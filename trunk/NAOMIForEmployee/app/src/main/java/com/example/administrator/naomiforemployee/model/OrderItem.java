package com.example.administrator.naomiforemployee.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 7/13/2017.
 */

public class OrderItem implements Parcelable {

    public static final Creator<OrderItem> CREATOR = new Creator<OrderItem>() {
        @Override
        public OrderItem createFromParcel(Parcel in) {
            return new OrderItem(in);
        }

        @Override
        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };
    public int quantity;
    public int productId;
    public String productName;
    public double total;
    public int orderId;

    public OrderItem() {
    }

    public OrderItem(int quantity, int productId, String productName, double total, int orderId) {
        this.quantity = quantity;
        this.productId = productId;
        this.productName = productName;
        this.total = total;
        this.orderId = orderId;
    }

    public OrderItem(int quantity, int productId, String productName, double total) {
        this.quantity = quantity;
        this.productId = productId;
        this.productName = productName;
        this.total = total;
    }

    protected OrderItem(Parcel in) {
        quantity = in.readInt();
        productId = in.readInt();
        productName = in.readString();
        total = in.readDouble();
        orderId = in.readInt();
    }

    public static Creator<OrderItem> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(quantity);
        dest.writeInt(productId);
        dest.writeString(productName);
        dest.writeDouble(total);
        dest.writeInt(orderId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getOrderId() {

        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
