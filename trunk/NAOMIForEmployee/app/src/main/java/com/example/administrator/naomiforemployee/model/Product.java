package com.example.administrator.naomiforemployee.model;

/**
 * Created by Administrator on 7/11/2017.
 */

public class Product {

    public int productID;
    public String productName;
    public double productPrice;
    public int productStatus;

    public Product() {
    }

    public Product(int productID, String productName, double productPrice, int productStatus) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }
}
