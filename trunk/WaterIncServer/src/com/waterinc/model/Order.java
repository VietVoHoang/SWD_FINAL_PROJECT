package com.waterinc.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Asus on 7/2/2017.
 */
@Entity
public class Order {
    private Integer orderId;
    private Timestamp orderCreateDate;
    private Double orderTotal;
    private Integer orderStatus;
    private Integer employeeEmployeeId;
    private Collection<Oderitem> oderitemsByOrderId;
    private Employee employeeByEmployeeEmployeeId;

    @Id
    @Column(name = "orderId", nullable = false)
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
    @Column(name = "Employee_employeeId", nullable = false)
    public Integer getEmployeeEmployeeId() {
        return employeeEmployeeId;
    }

    public void setEmployeeEmployeeId(Integer employeeEmployeeId) {
        this.employeeEmployeeId = employeeEmployeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != null ? !orderId.equals(order.orderId) : order.orderId != null) return false;
        if (orderCreateDate != null ? !orderCreateDate.equals(order.orderCreateDate) : order.orderCreateDate != null)
            return false;
        if (orderTotal != null ? !orderTotal.equals(order.orderTotal) : order.orderTotal != null) return false;
        if (orderStatus != null ? !orderStatus.equals(order.orderStatus) : order.orderStatus != null) return false;
        if (employeeEmployeeId != null ? !employeeEmployeeId.equals(order.employeeEmployeeId) : order.employeeEmployeeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (orderCreateDate != null ? orderCreateDate.hashCode() : 0);
        result = 31 * result + (orderTotal != null ? orderTotal.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (employeeEmployeeId != null ? employeeEmployeeId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "orderByOrderOrderId")
    public Collection<Oderitem> getOderitemsByOrderId() {
        return oderitemsByOrderId;
    }

    public void setOderitemsByOrderId(Collection<Oderitem> oderitemsByOrderId) {
        this.oderitemsByOrderId = oderitemsByOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "Employee_employeeId", referencedColumnName = "employeeId", nullable = false, insertable = false, updatable = false)
    public Employee getEmployeeByEmployeeEmployeeId() {
        return employeeByEmployeeEmployeeId;
    }

    public void setEmployeeByEmployeeEmployeeId(Employee employeeByEmployeeEmployeeId) {
        this.employeeByEmployeeEmployeeId = employeeByEmployeeEmployeeId;
    }
}
