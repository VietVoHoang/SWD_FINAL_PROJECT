package com.waterinc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waterinc.model.Orderitems;
import com.waterinc.repositories.OrderItemRepository;
import com.waterinc.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Asus on 7/15/2017.
 */
@RestController
@EnableWebMvc
public class OrderItemsController {
    @Autowired
    OrderItemRepository orderItemRepository;

    @JsonView(View.OrderItemView.class)
    @RequestMapping(value = "getOrderItemByOrderId", method = RequestMethod.POST)
    public List<Orderitems> getOrderItemByOrderId(int id) {
        return orderItemRepository.findAllByOrderId(id);
    }

    @JsonView(View.OrderItemView.class)
    @RequestMapping(value = "getOrderItemByOrderIdMobile", method = RequestMethod.POST)
    public List<Orderitems> getOrderItemByOrderIdMobile(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderItemRepository.findAllByOrderId((Integer) map.get("orderId"));
    }

    @JsonView(View.OrderItemView.class)
    @RequestMapping(value = "addOrderItem", method = RequestMethod.POST)
    public List<Orderitems> addOrderItem(int orderId, int productId, int quantity) {
        System.out.println("addOrderItem:" + orderId + " - " + productId + " - " + quantity);
        List<Orderitems> list = getOrderItemByOrderId(orderId);
        for (Orderitems orderitems : list) {
            if (orderitems.getProductId() == productId) {
                int quan = orderitems.getItemQuantity() + quantity;
                orderitems.setItemQuantity(quan);
                orderItemRepository.save(orderitems);
                return getOrderItemByOrderId(orderId);
            }
        }
        Orderitems o = new Orderitems();
        o.setProductId(productId);
        o.setOrderId(orderId);
        o.setItemQuantity(quantity);
        orderItemRepository.save(o);
        return getOrderItemByOrderId(orderId);
    }

    @RequestMapping(value = "updateOrderItemByOrderIdAndProductId", method = RequestMethod.POST)
    public void updateOrderItemByOrderIdAndProductId(int orderId, int productId, int newQuantity) {
        List<Orderitems> list = orderItemRepository.findAllByOrderId(orderId);
        for (Orderitems o : list) {
            if (o.getProductId() == productId) {
                o.setItemQuantity(newQuantity);
                orderItemRepository.save(o);
            }
        }
    }
}
