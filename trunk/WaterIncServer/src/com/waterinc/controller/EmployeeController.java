package com.waterinc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.model.Employees;
import com.waterinc.model.Users;
import com.waterinc.repositories.EmployeeRepository;
import com.waterinc.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by hongducphan on 7/3/17.
 */
@RestController
@EnableWebMvc
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @JsonView(View.EmployeeView.class)
    @RequestMapping(value = "getAllEmployee", method = RequestMethod.GET)
    public List<Employees> getAllEmployee() {
        List<Employees> result = employeeRepository.findAll();
//        System.out.println(result.size());
        return result;
    }

    @JsonView(View.EmployeeView.class)
    @RequestMapping(value = "addEmployee", method = RequestMethod.POST)
    public List<Employees> addNewEmployee(String name, double baseSalary, String titleName) {
        Employees employee = new Employees();
        employee.setName(name);
        employee.setStatus(1);
        employee.setDayOff(0);
        employee.setBaseSalary(baseSalary);
        employee.setTitleName(titleName);
        employee.setBonusDay(0);
        employee.setHourOff(0);
        employee.setBonusHour(0);
        employeeRepository.save(employee);
        return getAllEmployee();
    }

    @JsonView(View.EmployeeView.class)
    @RequestMapping(value = "updateEmployee", method = RequestMethod.POST)
    public List<Employees> updateEmployee(int id, String name, int status, int dayOff, double baseSalary, String titleName, int bonusDay, int hourOff, int bonusHour) {
        Employees employee = employeeRepository.findOne(id);
        employee.setName(name);
        employee.setStatus(status);
        employee.setDayOff(dayOff);
        employee.setBaseSalary(baseSalary);
        employee.setTitleName(titleName);
        employee.setBonusDay(bonusDay);
        employee.setHourOff(hourOff);
        employee.setBonusHour(bonusHour);
        employeeRepository.save(employee);
        return getAllEmployee();
    }

    @JsonView(View.EmployeeView.class)
    @Transactional
    @RequestMapping(value = "removeEmployee", method = RequestMethod.POST)
    public List<Employees> removeEmployee(int id) {
        Employees employee = employeeRepository.findOne(id);
        if(employee != null) {
            employee.setStatus(0);
        }
        employeeRepository.save(employee);
        return getAllEmployee();
    }

    @JsonView({View.EmployeeView.class})
    @RequestMapping(value = "findEmpById", method = RequestMethod.POST)
    public Employees findEmpById(int id) {
        return  employeeRepository.findOne(id);
    }
}
