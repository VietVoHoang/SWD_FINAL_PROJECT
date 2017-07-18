package com.waterinc.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.view.View;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by hongducphan on 7/13/17.
 */
@Entity
public class Products {
    @JsonView(View.ProductView.class)
    private Integer id;

    @JsonView({View.ProductView.class, View.OrderItemView.class})
    private String productName;

    @JsonView(View.ProductView.class)
    private Integer productQuantity;

    @JsonView(View.ProductView.class)
    private Double productPrice;

    @JsonView(View.ProductView.class)
    private Integer status;

    private Collection<Orderitems> orderitemsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Products products = (Products) o;

        if (id != null ? !id.equals(products.id) : products.id != null) return false;
        if (productName != null ? !productName.equals(products.productName) : products.productName != null)
            return false;
        if (productQuantity != null ? !productQuantity.equals(products.productQuantity) : products.productQuantity != null)
            return false;
        if (productPrice != null ? !productPrice.equals(products.productPrice) : products.productPrice != null)
            return false;
        if (status != null ? !status.equals(products.status) : products.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productQuantity != null ? productQuantity.hashCode() : 0);
        result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "productsByProductId")
    public Collection<Orderitems> getOrderitemsById() {
        return orderitemsById;
    }

    public void setOrderitemsById(Collection<Orderitems> orderitemsById) {
        this.orderitemsById = orderitemsById;
    }
}
