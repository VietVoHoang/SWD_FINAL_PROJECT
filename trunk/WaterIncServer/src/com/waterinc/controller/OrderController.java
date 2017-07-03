package com.waterinc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.model.Order;
import com.waterinc.repositories.OrderRepository;
import com.waterinc.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by hongducphan on 7/3/17.
 */
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @JsonView(View.ProductView.class)
    @RequestMapping(value = "findAllOrder", method = RequestMethod.GET)
    public List<Order> findAllOrder() {
        List<Order> result = orderRepository.findAll();
        System.out.println(result.size());
        return result;
    }

    @RequestMapping(value = "addOrder", method = RequestMethod.POST)
    public Order addNewOrder(Timestamp createDate, double total, int status) {
        Order order = new Order();
        order.setOrderCreateDate(createDate);
        order.setOrderTotal(total);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    @RequestMapping(value = "updateOrder", method = RequestMethod.POST)
    public Order updateOrder(int id, Timestamp createDate, double total, int status) {
        Order order = orderRepository.findOne(id);
        order.setOrderCreateDate(createDate);
        order.setOrderTotal(total);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    @RequestMapping(value = "removeProduct", method = RequestMethod.POST)
    public void removeOrder(int id) {
        Order order = orderRepository.findOne(id);
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
