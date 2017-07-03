package com.waterinc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.model.Employee;
import com.waterinc.repositories.EmployeeRepository;
import com.waterinc.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by hongducphan on 7/3/17.
 */
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @JsonView(View.AgencyView.class)
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<Employee> getAllEmployee() {
        List<Employee> result = employeeRepository.findAll();
        System.out.println(result.size());
        return result;
    }

    @RequestMapping(value = "addEmployee", method = RequestMethod.POST)
    public Employee addNewEmployee(String name, int agencyId, int status) {
        Employee employee = new Employee();
        employee.setEmployeeName(name);
        employee.setAgencyAgencyId(agencyId);
        employee.setStatus(status);
        return employeeRepository.save(employee);
    }

    @RequestMapping(value = "updateEmployee", method = RequestMethod.POST)
    public Employee updateEmployee(int id, String name, int agencyId, int status) {
        Employee employee = employeeRepository.findOne(id);
        employee.setEmployeeName(name);
        employee.setAgencyAgencyId(agencyId);
        employee.setStatus(status);
        return employeeRepository.save(employee);
    }

    @RequestMapping(value = "removeEmployee", method = RequestMethod.POST)
    public void removeEmployee(int id) {
        Employee employee = employeeRepository.findOne(id);
        if(employee != null) {
            employee.setStatus(0);
        }
        employeeRepository.save(employee);
    }
}
