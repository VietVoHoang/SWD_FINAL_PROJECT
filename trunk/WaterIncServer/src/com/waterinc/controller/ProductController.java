package com.waterinc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.model.Product;
import com.waterinc.repositories.ProductRepository;
import com.waterinc.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 6/29/2017.
 */
@RestController
@EnableWebMvc
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @JsonView(View.ProductView.class)
    @RequestMapping(value = "findAllProduct", method = RequestMethod.GET)
    public List<Product> findAllProduct() {
        List<Product> result = productRepository.findAll();
        System.out.println(result.size());
        return result;
    }

    @RequestMapping(value = "addProduct", method = RequestMethod.POST)
    public Product addNewProduct(String productName, int quantity, double price) {
        Product product = new Product();
        product.setProductName(productName);
        product.setProductQuantity(quantity);
        product.setProductPrice(price);
        product.setStatus(1);
        return productRepository.save(product);
    }

    @RequestMapping(value = "updateProduct", method = RequestMethod.POST)
    public Product updateProduct(int id, String productName, int quantity, double price) {
        Product product = productRepository.findOne(id);
        product.setProductName(productName);
        product.setProductQuantity(quantity);
        product.setProductPrice(price);
        product.setStatus(1);
        return productRepository.save(product);
    }

    @RequestMapping(value = "removeProduct", method = RequestMethod.POST)
    public void removeProduct(int id) {
        Product product = productRepository.findOne(id);
        if (product != null) {
            productRepository.delete(product);
        }
    }

    @JsonView(View.ProductView.class)
    @RequestMapping(value = "searchProductByName", method = RequestMethod.POST)
    public List<Product> searchProductByName(String searchValue) {
        List<Product> productList = new ArrayList<>();
        return productList;
    }
}
