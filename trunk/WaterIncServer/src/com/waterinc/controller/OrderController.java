package com.waterinc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waterinc.model.Orders;
import com.waterinc.repositories.OrderRepository;
import com.waterinc.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by hongducphan on 7/3/17.
 */
@RestController
@EnableWebMvc
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @JsonView(View.OrderView.class)
    @RequestMapping(value = "findAllOrder", method = RequestMethod.GET)
    public List findAllOrder() {
        List<Orders> result = new ArrayList<>();
        try {
            result = orderRepository.findAll();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        Collections.reverse(result);
        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).getCustomerName());
        }
        return result;
    }

    @RequestMapping(value = "addOrder", method = RequestMethod.POST)
    public Orders addNewOrder(Timestamp createDate,
                              double total,
                              int status,
                              String cusName,
                              String cusPhone,
                              String cusAddress,
                              int empId) {
        Orders order = new Orders();
        order.setOrderCreateDate(createDate);
        order.setOrderTotal(total);
        order.setOrderStatus(status);
        order.setCustomerName(cusName);
        order.setCustomerAddress(cusAddress);
        order.setCustomerPhone(cusPhone);
        order.setEmployeeId(empId);
        return orderRepository.save(order);
    }

    @RequestMapping(value = "addOrderMobile", method = RequestMethod.POST)
    public int addNewOrderTest(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(map);
        Orders order = new Orders();
        Timestamp ts = Timestamp.valueOf((String) map.get("createDate"));
        order.setOrderCreateDate(ts);
        order.setOrderTotal(((Integer) map.get("total")) * 1.0);
        order.setOrderStatus((Integer) map.get("status"));
        order.setCustomerName((String) map.get("cusName"));
        order.setCustomerAddress((String) map.get("cusAddress"));
        order.setCustomerPhone((String) map.get("cusPhone"));
        return orderRepository.save(order).getId();
//        return 0;
    }

    @RequestMapping(value = "updateOrder", method = RequestMethod.POST)
    public Orders updateOrder(int id, Timestamp createDate, double total, int status) {
        Orders order = orderRepository.findOne(id);
        order.setOrderCreateDate(createDate);
        order.setOrderTotal(total);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    @RequestMapping(value = "removeOrder", method = RequestMethod.POST)
    public void removeOrder(int id) {
        Orders order = orderRepository.findOne(id);
        if (order != null) {
            orderRepository.delete(order);
        }
    }

//    @JsonView(View.ProductView.class)
//    @RequestMapping(value = "searchProductByName", method = RequestMethod.POST)
//    public List<Product> searchProductByName(String searchValue) {
//        List<Product> productList = new ArrayList<>();
//        return productList;
//    }
}
