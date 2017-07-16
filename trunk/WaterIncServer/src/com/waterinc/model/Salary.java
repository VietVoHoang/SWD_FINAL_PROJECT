package com.waterinc.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.view.View;

import javax.persistence.*;

/**
 * Created by hongducphan on 7/13/17.
 */
@Entity
public class Salary {
    @JsonView(View.SalaryView.class)
    private Integer id;

    @JsonView(View.SalaryView.class)
    private Double salaryByDay;

    @JsonView(View.SalaryView.class)
    private Double salaryByHour;

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
    @Column(name = "salaryByDay", nullable = false, precision = 0)
    public Double getSalaryByDay() {
        return salaryByDay;
    }

    public void setSalaryByDay(Double salaryByDay) {
        this.salaryByDay = salaryByDay;
    }

    @Basic
    @Column(name = "salaryByHour", nullable = false, precision = 0)
    public Double getSalaryByHour() {
        return salaryByHour;
    }

    public void setSalaryByHour(Double salaryByHour) {
        this.salaryByHour = salaryByHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salary salary = (Salary) o;

        if (id != null ? !id.equals(salary.id) : salary.id != null) return false;
        if (salaryByDay != null ? !salaryByDay.equals(salary.salaryByDay) : salary.salaryByDay != null) return false;
        if (salaryByHour != null ? !salaryByHour.equals(salary.salaryByHour) : salary.salaryByHour != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (salaryByDay != null ? salaryByDay.hashCode() : 0);
        result = 31 * result + (salaryByHour != null ? salaryByHour.hashCode() : 0);
        return result;
    }
}
