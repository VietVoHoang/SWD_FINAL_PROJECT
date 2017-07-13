package com.waterinc.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by hongducphan on 7/13/17.
 */
@Entity
public class Orders {
    private Integer id;
    private Timestamp orderCreateDate;
    private Double orderTotal;
    private Integer orderStatus;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private Integer employeeId;
    private Collection<Orderitems> orderitemsById;
    private Employees employeesByEmployeeId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "orderCreateDate", nullable = false)
    public Timestamp getOrderCreateDate() {
        return orderCreateDate;
    }

    public void setOrderCreateDate(Timestamp orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
    }

    @Basic
    @Column(name = "orderTotal", nullable = false, precision = 0)
    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Basic
    @Column(name = "orderStatus", nullable = false)
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "customerName", nullable = false, length = 45)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "customerPhone", nullable = false, length = 11)
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Basic
    @Column(name = "customerAddress", nullable = false, length = 100)
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Basic
    @Column(name = "Employee_id", nullable = false)
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (id != null ? !id.equals(orders.id) : orders.id != null) return false;
        if (orderCreateDate != null ? !orderCreateDate.equals(orders.orderCreateDate) : orders.orderCreateDate != null)
            return false;
        if (orderTotal != null ? !orderTotal.equals(orders.orderTotal) : orders.orderTotal != null) return false;
        if (orderStatus != null ? !orderStatus.equals(orders.orderStatus) : orders.orderStatus != null) return false;
        if (customerName != null ? !customerName.equals(orders.customerName) : orders.customerName != null)
            return false;
        if (customerPhone != null ? !customerPhone.equals(orders.customerPhone) : orders.customerPhone != null)
            return false;
        if (customerAddress != null ? !customerAddress.equals(orders.customerAddress) : orders.customerAddress != null)
            return false;
        if (employeeId != null ? !employeeId.equals(orders.employeeId) : orders.employeeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderCreateDate != null ? orderCreateDate.hashCode() : 0);
        result = 31 * result + (orderTotal != null ? orderTotal.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (customerPhone != null ? customerPhone.hashCode() : 0);
        result = 31 * result + (customerAddress != null ? customerAddress.hashCode() : 0);
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "ordersByOrderId")
    public Collection<Orderitems> getOrderitemsById() {
        return orderitemsById;
    }

    public void setOrderitemsById(Collection<Orderitems> orderitemsById) {
        this.orderitemsById = orderitemsById;
    }

    @ManyToOne
    @JoinColumn(name = "Employee_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Employees getEmployeesByEmployeeId() {
        return employeesByEmployeeId;
    }

    public void setEmployeesByEmployeeId(Employees employeesByEmployeeId) {
        this.employeesByEmployeeId = employeesByEmployeeId;
    }
}
