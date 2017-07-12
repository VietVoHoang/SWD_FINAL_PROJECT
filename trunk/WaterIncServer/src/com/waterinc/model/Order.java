package com.waterinc.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Asus on 7/12/2017.
 */
@Entity
public class Order {
    private Integer id;
    private Timestamp orderCreateDate;
    private Double orderTotal;
    private Integer orderStatus;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private Integer employeeId;
    private Employee employeeByEmployeeId;
    private Collection<Orderitem> orderitemsById;

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

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (orderCreateDate != null ? !orderCreateDate.equals(order.orderCreateDate) : order.orderCreateDate != null)
            return false;
        if (orderTotal != null ? !orderTotal.equals(order.orderTotal) : order.orderTotal != null) return false;
        if (orderStatus != null ? !orderStatus.equals(order.orderStatus) : order.orderStatus != null) return false;
        if (customerName != null ? !customerName.equals(order.customerName) : order.customerName != null) return false;
        if (customerPhone != null ? !customerPhone.equals(order.customerPhone) : order.customerPhone != null)
            return false;
        if (customerAddress != null ? !customerAddress.equals(order.customerAddress) : order.customerAddress != null)
            return false;
        if (employeeId != null ? !employeeId.equals(order.employeeId) : order.employeeId != null) return false;

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

    @ManyToOne
    @JoinColumn(name = "Employee_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Employee getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(Employee employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }

    @OneToMany(mappedBy = "orderByOrderId")
    public Collection<Orderitem> getOrderitemsById() {
        return orderitemsById;
    }

    public void setOrderitemsById(Collection<Orderitem> orderitemsById) {
        this.orderitemsById = orderitemsById;
    }
}
