package com.waterinc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.model.Orderitems;
import com.waterinc.model.Orders;
import com.waterinc.repositories.OrderItemRepository;
import com.waterinc.repositories.OrderRepository;
import com.waterinc.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hongducphan on 7/3/17.
 */
@RestController
@EnableWebMvc
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

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
        return result;
    }

    @JsonView(View.OrderView.class)
    @RequestMapping(value = "findOrderById", method = RequestMethod.POST)
    public Orders findOrderById(int id) {
        return orderRepository.findOne(id);
    }

    @JsonView(View.OrderView.class)
    @RequestMapping(value = "findAllOrderNotDelivered", method = RequestMethod.GET)
    public List findAllOrderNotDelivered() {
        List<Orders> result = new ArrayList<>();
        try {
            result = orderRepository.findAllByOrderStatus(2);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return result;
    }

    @JsonView(View.OrderView.class)
    @RequestMapping(value = "UpdateStatusOrderDelivered", method = RequestMethod.POST)
    public List UpdateOrderStatusMobile(int orderId, int status) {
        System.out.println(orderId + " - " + status);
        Orders result = orderRepository.findOne(orderId);
        result.setOrderStatus(status);
        orderRepository.save(result);
        return findAllOrderNotDelivered();
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
    public ResponseEntity<Integer> addNewOrderTest(Timestamp createDate,
                                                   double total,
                                                   int status,
                                                   String cusName,
                                                   String cusPhone,
                                                   String cusAddress, int empId) {
        System.out.println(createDate + " - " + total + " - " + status + " - " + cusName + " - " + cusPhone + " - " + cusAddress + " - " + total + " - " + empId);
        Orders order = new Orders();
        order.setOrderCreateDate(createDate);
        order.setOrderTotal(total);
        order.setOrderStatus(status);
        order.setCustomerName(cusName);
        order.setCustomerAddress(cusAddress);
        order.setCustomerPhone(cusPhone);
        order.setEmployeeId(empId);
        return new ResponseEntity(orderRepository.save(order), HttpStatus.OK);
    }

    @JsonView(View.OrderView.class)
    @RequestMapping(value = "updateOrder", method = RequestMethod.POST)
    public Orders updateOrder(int id, double total, int status) {
        System.out.println(id + " - " + total + " - " + status);
        Orders order = orderRepository.findOne(id);
        order.setOrderTotal(total);
        order.setOrderStatus(status);
        System.out.println(order);
        return orderRepository.save(order);
    }


    @JsonView(View.OrderView.class)
    @RequestMapping(value = "removeOrder", method = RequestMethod.POST)
    @Transactional
    public List<Orders> removeOrder(int id) {
        List<Orderitems> list = orderItemRepository.findAllByOrderId(id);
        for (Orderitems o : list) {
            orderItemRepository.delete(o);
        }
        Orders order = orderRepository.findOne(id);
        if (order != null && order.getOrderStatus() == 0) {
            orderRepository.delete(order);
        }
        return findAllOrder();
    }
}
