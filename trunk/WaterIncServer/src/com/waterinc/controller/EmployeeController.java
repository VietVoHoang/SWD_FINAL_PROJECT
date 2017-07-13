package com.waterinc.controller;

import com.waterinc.model.Employees;
import com.waterinc.repositories.EmployeeRepository;
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

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<Employees> getAllEmployee() {
        List<Employees> result = employeeRepository.findAll();
        System.out.println(result.size());
        return result;
    }

    @RequestMapping(value = "addEmployee", method = RequestMethod.POST)
    public Employees addNewEmployee(String name, int agencyId, int status) {
        Employees employee = new Employees();

        employee.setStatus(status);
        return employeeRepository.save(employee);
    }

    @RequestMapping(value = "updateEmployee", method = RequestMethod.POST)
    public Employees updateEmployee(int id, String name, int agencyId, int status) {
        Employees employee = employeeRepository.findOne(id);

        employee.setStatus(status);
        return employeeRepository.save(employee);
    }

    @RequestMapping(value = "removeEmployee", method = RequestMethod.POST)
    public void removeEmployee(int id) {
        Employees employee = employeeRepository.findOne(id);
        if(employee != null) {
            employee.setStatus(0);
        }
        employeeRepository.save(employee);
    }
}
