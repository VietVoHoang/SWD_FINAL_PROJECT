package com.waterinc.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.view.View;

import javax.persistence.*;

/**
 * Created by hongducphan on 7/13/17.
 */
@Entity
public class Bonuscoefficient {
    @JsonView(View.BonuscoefficientView.class)
    private Integer id;

    @JsonView(View.BonuscoefficientView.class)
    private Integer coefficientByDay;

    @JsonView(View.BonuscoefficientView.class)
    private Integer coefficientByHour;

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
    @Column(name = "coefficientByDay", nullable = false)
    public Integer getCoefficientByDay() {
        return coefficientByDay;
    }

    public void setCoefficientByDay(Integer coefficientByDay) {
        this.coefficientByDay = coefficientByDay;
    }

    @Basic
    @Column(name = "coefficientByHour", nullable = false)
    public Integer getCoefficientByHour() {
        return coefficientByHour;
    }

    public void setCoefficientByHour(Integer coefficientByHour) {
        this.coefficientByHour = coefficientByHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bonuscoefficient that = (Bonuscoefficient) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (coefficientByDay != null ? !coefficientByDay.equals(that.coefficientByDay) : that.coefficientByDay != null)
            return false;
        if (coefficientByHour != null ? !coefficientByHour.equals(that.coefficientByHour) : that.coefficientByHour != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (coefficientByDay != null ? coefficientByDay.hashCode() : 0);
        result = 31 * result + (coefficientByHour != null ? coefficientByHour.hashCode() : 0);
        return result;
    }
}
