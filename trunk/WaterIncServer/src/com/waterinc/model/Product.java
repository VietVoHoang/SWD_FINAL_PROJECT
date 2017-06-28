package com.waterinc.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Asus on 6/28/2017.
 */
@Entity
public class Product {
    private Integer productId;
    private String productName;
    private Integer productQuantity;
    private Double productPrice;
    private Collection<Oderitem> oderitemsByProductId;

    @Id
    @Column(name = "productId", nullable = false)
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "productName", nullable = false, length = 45)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "productQuantity", nullable = false)
    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Basic
    @Column(name = "productPrice", nullable = false, precision = 0)
    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != null ? !productId.equals(product.productId) : product.productId != null) return false;
        if (productName != null ? !productName.equals(product.productName) : product.productName != null) return false;
        if (productQuantity != null ? !productQuantity.equals(product.productQuantity) : product.productQuantity != null)
            return false;
        if (productPrice != null ? !productPrice.equals(product.productPrice) : product.productPrice != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productQuantity != null ? productQuantity.hashCode() : 0);
        result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "productByProductProductId")
    public Collection<Oderitem> getOderitemsByProductId() {
        return oderitemsByProductId;
    }

    public void setOderitemsByProductId(Collection<Oderitem> oderitemsByProductId) {
        this.oderitemsByProductId = oderitemsByProductId;
    }
}
