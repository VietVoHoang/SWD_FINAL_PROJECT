package com.waterinc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.model.Agency;
import com.waterinc.repositories.AgencyRepository;
import com.waterinc.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;


/**
 * Created by Asus on 6/28/2017.
 */
@RestController
@EnableWebMvc
public class AgencyController {
    @Autowired
    AgencyRepository agencyRepository;

    @JsonView(View.AgencyView.class)
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<Agency> getAllAgency() {
        List<Agency> result = agencyRepository.findAll();
        System.out.println(result.size());
        return result;
    }
}
