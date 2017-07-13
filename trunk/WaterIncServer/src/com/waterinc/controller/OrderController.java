package com.waterinc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.model.Orders;
import com.waterinc.repositories.OrderRepository;
import com.waterinc.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hongducphan on 7/3/17.
 */
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
    public Orders addNewOrder(Timestamp createDate, double total, int status) {
        Orders order = new Orders();
        order.setOrderCreateDate(createDate);
        order.setOrderTotal(total);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    @RequestMapping(value = "updateOrder", method = RequestMethod.POST)
    public Orders updateOrder(int id, Timestamp createDate, double total, int status) {
        Orders order = orderRepository.findOne(id);
        order.setOrderCreateDate(createDate);
        order.setOrderTotal(total);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    @RequestMapping(value = "removeProduct", method = RequestMethod.POST)
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
