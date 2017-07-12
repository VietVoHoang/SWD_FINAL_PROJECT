package com.waterinc.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Asus on 7/12/2017.
 */
@Entity
public class Employee {
    private Integer id;
    private String name;
    private Integer status;
    private Integer dayOff;
    private Double baseSalary;
    private String titleName;
    private Integer bonusDay;
    private Integer hourOff;
    private Integer bonusHour;
    private Collection<Order> ordersById;
    private Collection<User> usersById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "dayOff", nullable = false)
    public Integer getDayOff() {
        return dayOff;
    }

    public void setDayOff(Integer dayOff) {
        this.dayOff = dayOff;
    }

    @Basic
    @Column(name = "baseSalary", nullable = false, precision = 0)
    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Basic
    @Column(name = "titleName", nullable = false, length = 45)
    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    @Basic
    @Column(name = "bonusDay", nullable = false)
    public Integer getBonusDay() {
        return bonusDay;
    }

    public void setBonusDay(Integer bonusDay) {
        this.bonusDay = bonusDay;
    }

    @Basic
    @Column(name = "hourOff", nullable = false)
    public Integer getHourOff() {
        return hourOff;
    }

    public void setHourOff(Integer hourOff) {
        this.hourOff = hourOff;
    }

    @Basic
    @Column(name = "bonusHour", nullable = false)
    public Integer getBonusHour() {
        return bonusHour;
    }

    public void setBonusHour(Integer bonusHour) {
        this.bonusHour = bonusHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != null ? !id.equals(employee.id) : employee.id != null) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (status != null ? !status.equals(employee.status) : employee.status != null) return false;
        if (dayOff != null ? !dayOff.equals(employee.dayOff) : employee.dayOff != null) return false;
        if (baseSalary != null ? !baseSalary.equals(employee.baseSalary) : employee.baseSalary != null) return false;
        if (titleName != null ? !titleName.equals(employee.titleName) : employee.titleName != null) return false;
        if (bonusDay != null ? !bonusDay.equals(employee.bonusDay) : employee.bonusDay != null) return false;
        if (hourOff != null ? !hourOff.equals(employee.hourOff) : employee.hourOff != null) return false;
        if (bonusHour != null ? !bonusHour.equals(employee.bonusHour) : employee.bonusHour != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (dayOff != null ? dayOff.hashCode() : 0);
        result = 31 * result + (baseSalary != null ? baseSalary.hashCode() : 0);
        result = 31 * result + (titleName != null ? titleName.hashCode() : 0);
        result = 31 * result + (bonusDay != null ? bonusDay.hashCode() : 0);
        result = 31 * result + (hourOff != null ? hourOff.hashCode() : 0);
        result = 31 * result + (bonusHour != null ? bonusHour.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
    public Collection<Order> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<Order> ordersById) {
        this.ordersById = ordersById;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
    public Collection<User> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<User> usersById) {
        this.usersById = usersById;
    }
}
