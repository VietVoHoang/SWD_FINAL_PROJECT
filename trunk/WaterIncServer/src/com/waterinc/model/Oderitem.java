package com.waterinc.model;

import javax.persistence.*;

/**
 * Created by Asus on 6/29/2017.
 */
@Entity
public class Oderitem {
    private Integer itemQuantity;
    private Integer productProductId;
    private Integer orderOrderId;
    private Integer itemId;
    private Product productByProductProductId;
    private Order orderByOrderOrderId;

    @Basic
    @Column(name = "itemQuantity", nullable = false)
    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Basic
    @Column(name = "Product_productId", nullable = false)
    public Integer getProductProductId() {
        return productProductId;
    }

    public void setProductProductId(Integer productProductId) {
        this.productProductId = productProductId;
    }

    @Basic
    @Column(name = "Order_orderId", nullable = false)
    public Integer getOrderOrderId() {
        return orderOrderId;
    }

    public void setOrderOrderId(Integer orderOrderId) {
        this.orderOrderId = orderOrderId;
    }

    @Id
    @Column(name = "itemId", nullable = false)
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Oderitem oderitem = (Oderitem) o;

        if (itemQuantity != null ? !itemQuantity.equals(oderitem.itemQuantity) : oderitem.itemQuantity != null)
            return false;
        if (productProductId != null ? !productProductId.equals(oderitem.productProductId) : oderitem.productProductId != null)
            return false;
        if (orderOrderId != null ? !orderOrderId.equals(oderitem.orderOrderId) : oderitem.orderOrderId != null)
            return false;
        if (itemId != null ? !itemId.equals(oderitem.itemId) : oderitem.itemId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemQuantity != null ? itemQuantity.hashCode() : 0;
        result = 31 * result + (productProductId != null ? productProductId.hashCode() : 0);
        result = 31 * result + (orderOrderId != null ? orderOrderId.hashCode() : 0);
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Product_productId", referencedColumnName = "productId", nullable = false, insertable = false, updatable = false)
    public Product getProductByProductProductId() {
        return productByProductProductId;
    }

    public void setProductByProductProductId(Product productByProductProductId) {
        this.productByProductProductId = productByProductProductId;
    }

    @ManyToOne
    @JoinColumn(name = "Order_orderId", referencedColumnName = "orderId", nullable = false, insertable = false, updatable = false)
    public Order getOrderByOrderOrderId() {
        return orderByOrderOrderId;
    }

    public void setOrderByOrderOrderId(Order orderByOrderOrderId) {
        this.orderByOrderOrderId = orderByOrderOrderId;
    }
}
