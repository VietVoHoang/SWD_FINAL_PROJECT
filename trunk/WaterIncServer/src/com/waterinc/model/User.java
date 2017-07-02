package com.waterinc.model;

import javax.persistence.*;

/**
 * Created by Asus on 7/2/2017.
 */
@Entity
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer enable;
    private String role;
    private Integer employeeEmployeeId;
    private Employee employeeByEmployeeEmployeeId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "enable", nullable = true)
    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "role", nullable = true, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "employee_employeeId", nullable = false)
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

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (enable != null ? !enable.equals(user.enable) : user.enable != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (employeeEmployeeId != null ? !employeeEmployeeId.equals(user.employeeEmployeeId) : user.employeeEmployeeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (enable != null ? enable.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (employeeEmployeeId != null ? employeeEmployeeId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "employee_employeeId", referencedColumnName = "employeeId", nullable = false, insertable = false, updatable = false)
    public Employee getEmployeeByEmployeeEmployeeId() {
        return employeeByEmployeeEmployeeId;
    }

    public void setEmployeeByEmployeeEmployeeId(Employee employeeByEmployeeEmployeeId) {
        this.employeeByEmployeeEmployeeId = employeeByEmployeeEmployeeId;
    }
}
