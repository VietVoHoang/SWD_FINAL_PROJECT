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
    @RequestMapping(value = "findAllAgency", method = RequestMethod.GET)
    public List<Agency> getAllAgency() {
        List<Agency> result = agencyRepository.findAll();
        System.out.println(result.size());
        return result;
    }

    @RequestMapping(value = "addAgency", method = RequestMethod.POST)
    public Agency addNewAgency(String name, String address, int status) {
        Agency agency = new Agency();
        agency.setAgencyName(name);
        agency.setAgencyAddress(address);
        agency.setStatus(status);
        return agencyRepository.save(agency);
    }

    @RequestMapping(value = "updateAgency", method = RequestMethod.POST)
    public Agency updateAgency(int id, String name, String address, int status) {
        Agency agency = agencyRepository.findOne(id);
        agency.setAgencyName(name);
        agency.setAgencyAddress(address);
        agency.setStatus(status);
        return agencyRepository.save(agency);
    }

    @RequestMapping(value = "removeAgency", method = RequestMethod.POST)
    public void removeAgency(int id) {
        Agency agency = agencyRepository.findOne(id);
        if(agency != null) {
            agency.setStatus(0);
        }
        agencyRepository.save(agency);
    }

//    @JsonView(View.ProductView.class)
//    @RequestMapping(value = "searchProductByName", method = RequestMethod.POST)
//    public List<Product> searchProductByName(String searchValue) {
//        List<Product> productList = new ArrayList<>();
//        return productList;
//    }
}
