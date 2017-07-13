package com.waterinc.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by hongducphan on 7/13/17.
 */
@Entity
public class Employees {
    private Integer id;
    private String name;
    private Integer status;
    private Integer dayOff;
    private Double baseSalary;
    private String titleName;
    private Integer bonusDay;
    private Integer hourOff;
    private Integer bonusHour;
    private Collection<Orders> ordersById;
    private Collection<Users> usersById;

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

        Employees employees = (Employees) o;

        if (id != null ? !id.equals(employees.id) : employees.id != null) return false;
        if (name != null ? !name.equals(employees.name) : employees.name != null) return false;
        if (status != null ? !status.equals(employees.status) : employees.status != null) return false;
        if (dayOff != null ? !dayOff.equals(employees.dayOff) : employees.dayOff != null) return false;
        if (baseSalary != null ? !baseSalary.equals(employees.baseSalary) : employees.baseSalary != null) return false;
        if (titleName != null ? !titleName.equals(employees.titleName) : employees.titleName != null) return false;
        if (bonusDay != null ? !bonusDay.equals(employees.bonusDay) : employees.bonusDay != null) return false;
        if (hourOff != null ? !hourOff.equals(employees.hourOff) : employees.hourOff != null) return false;
        if (bonusHour != null ? !bonusHour.equals(employees.bonusHour) : employees.bonusHour != null) return false;

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

    @OneToMany(mappedBy = "employeesByEmployeeId")
    public Collection<Orders> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<Orders> ordersById) {
        this.ordersById = ordersById;
    }

    @OneToMany(mappedBy = "employeesByEmployeeId")
    public Collection<Users> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<Users> usersById) {
        this.usersById = usersById;
    }
}
