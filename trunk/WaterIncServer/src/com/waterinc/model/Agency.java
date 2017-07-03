package com.waterinc.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.view.View;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Asus on 7/2/2017.
 */
@Entity
public class Agency {
    private Integer agencyId;
    @JsonView({View.AgencyView.class})
    private String agencyName;
    private String agencyAddress;
    private Integer status;
    private Collection<Employee> employeesByAgencyId;

    @Id
    @Column(name = "agencyId", nullable = false)
    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    @Basic
    @Column(name = "agencyName", nullable = true, length = 45)
    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    @Basic
    @Column(name = "agencyAddress", nullable = true, length = 45)
    public String getAgencyAddress() {
        return agencyAddress;
    }

    public void setAgencyAddress(String agencyAddress) {
        this.agencyAddress = agencyAddress;
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

        Agency agency = (Agency) o;

        if (agencyId != null ? !agencyId.equals(agency.agencyId) : agency.agencyId != null) return false;
        if (agencyName != null ? !agencyName.equals(agency.agencyName) : agency.agencyName != null) return false;
        if (agencyAddress != null ? !agencyAddress.equals(agency.agencyAddress) : agency.agencyAddress != null)
            return false;
        if (status != null ? !status.equals(agency.status) : agency.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = agencyId != null ? agencyId.hashCode() : 0;
        result = 31 * result + (agencyName != null ? agencyName.hashCode() : 0);
        result = 31 * result + (agencyAddress != null ? agencyAddress.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "agencyByAgencyAgencyId")
    public Collection<Employee> getEmployeesByAgencyId() {
        return employeesByAgencyId;
    }

    public void setEmployeesByAgencyId(Collection<Employee> employeesByAgencyId) {
        this.employeesByAgencyId = employeesByAgencyId;
    }
}
