package com.waterinc.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Asus on 6/29/2017.
 */
@Entity
public class Employee {
    private Integer employeeId;
    private String employeeName;
    private Integer agencyAgencyId;
    private String employeeUsername;
    private String employeePassword;
    private Integer status;
    private Agency agencyByAgencyAgencyId;
    private Collection<Order> ordersByEmployeeId;

    @Id
    @Column(name = "employeeId", nullable = false)
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "employeeName", nullable = false, length = 45)
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Basic
    @Column(name = "Agency_agencyId", nullable = false)
    public Integer getAgencyAgencyId() {
        return agencyAgencyId;
    }

    public void setAgencyAgencyId(Integer agencyAgencyId) {
        this.agencyAgencyId = agencyAgencyId;
    }

    @Basic
    @Column(name = "employeeUsername", nullable = true, length = 45)
    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    @Basic
    @Column(name = "employeePassword", nullable = true, length = 45)
    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    @Basic
    @Column(name = "status", nullable = false)
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

        Employee employee = (Employee) o;

        if (employeeId != null ? !employeeId.equals(employee.employeeId) : employee.employeeId != null) return false;
        if (employeeName != null ? !employeeName.equals(employee.employeeName) : employee.employeeName != null)
            return false;
        if (agencyAgencyId != null ? !agencyAgencyId.equals(employee.agencyAgencyId) : employee.agencyAgencyId != null)
            return false;
        if (employeeUsername != null ? !employeeUsername.equals(employee.employeeUsername) : employee.employeeUsername != null)
            return false;
        if (employeePassword != null ? !employeePassword.equals(employee.employeePassword) : employee.employeePassword != null)
            return false;
        if (status != null ? !status.equals(employee.status) : employee.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeId != null ? employeeId.hashCode() : 0;
        result = 31 * result + (employeeName != null ? employeeName.hashCode() : 0);
        result = 31 * result + (agencyAgencyId != null ? agencyAgencyId.hashCode() : 0);
        result = 31 * result + (employeeUsername != null ? employeeUsername.hashCode() : 0);
        result = 31 * result + (employeePassword != null ? employeePassword.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Agency_agencyId", referencedColumnName = "agencyId", nullable = false, insertable = false, updatable = false)
    public Agency getAgencyByAgencyAgencyId() {
        return agencyByAgencyAgencyId;
    }

    public void setAgencyByAgencyAgencyId(Agency agencyByAgencyAgencyId) {
        this.agencyByAgencyAgencyId = agencyByAgencyAgencyId;
    }

    @OneToMany(mappedBy = "employeeByEmployeeEmployeeId")
    public Collection<Order> getOrdersByEmployeeId() {
        return ordersByEmployeeId;
    }

    public void setOrdersByEmployeeId(Collection<Order> ordersByEmployeeId) {
        this.ordersByEmployeeId = ordersByEmployeeId;
    }
}
