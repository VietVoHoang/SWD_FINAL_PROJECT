package com.waterinc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.model.Bonuscoefficient;
import com.waterinc.model.Salary;
import com.waterinc.repositories.BonuscoefficientRepository;
import com.waterinc.repositories.SalaryRepository;
import com.waterinc.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

/**
 * Created by hongducphan on 7/16/17.
 */

@RestController
@EnableWebMvc
public class BonuscoefficientController {
    @Autowired
    BonuscoefficientRepository bonuscoefficientRepository;

    @JsonView(View.BonuscoefficientView.class)
    @RequestMapping(value = "getAllBonuscoefficient", method = RequestMethod.GET)
    public List<Bonuscoefficient> findAllBonuscoefficient() {
        List<Bonuscoefficient> result = bonuscoefficientRepository.findAll();
        return result;
    }

    @JsonView(View.BonuscoefficientView.class)
    @RequestMapping(value = "updateBonuscoefficient", method = RequestMethod.POST)
    public List<Bonuscoefficient> updateBonuscoefficient(int id, int coefficientByDay, int coefficientByHour) {
        Bonuscoefficient bonuscoefficient = bonuscoefficientRepository.findOne(id);
        bonuscoefficient.setCoefficientByDay(coefficientByDay);
        bonuscoefficient.setCoefficientByHour(coefficientByHour);
        bonuscoefficientRepository.save(bonuscoefficient);
        return findAllBonuscoefficient();
    }
}
