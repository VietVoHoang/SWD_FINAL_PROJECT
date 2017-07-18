package com.waterinc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.model.Bonuscoefficient;
import com.waterinc.model.Products;
import com.waterinc.model.Salary;
import com.waterinc.repositories.ProductRepository;
import com.waterinc.repositories.SalaryRepository;
import com.waterinc.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongducphan on 7/16/17.
 */

@RestController
@EnableWebMvc
public class SalaryController {
    @Autowired
    SalaryRepository salaryRepository;

    @JsonView(View.SalaryView.class)
    @RequestMapping(value = "getAllSalary", method = RequestMethod.GET)
    public List<Salary> findAllSalary() {
        List<Salary> result = salaryRepository.findAll();
        return result;
    }

    @JsonView(View.SalaryView.class)
    @RequestMapping(value = "updateSalary", method = RequestMethod.POST)
    public List<Salary> updateSalary(int id, double salaryByDay, double salaryByHour) {
        Salary salary = salaryRepository.findOne(id);
        salary.setSalaryByDay(salaryByDay);
        salary.setSalaryByHour(salaryByHour);
        salaryRepository.save(salary);
        return findAllSalary();
    }

    @JsonView({View.SalaryView.class})
    @RequestMapping(value = "findSalaryById", method = RequestMethod.POST)
    public Salary findBonusById(int id) {
        return salaryRepository.findOne(id);
    }
}
