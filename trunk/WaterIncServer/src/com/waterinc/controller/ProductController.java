package com.waterinc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.model.Products;
import com.waterinc.model.Users;
import com.waterinc.repositories.ProductRepository;
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
 * Created by Asus on 6/29/2017.
 */
@RestController
@EnableWebMvc
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @JsonView(View.ProductView.class)
    @RequestMapping(value = "findAllProduct", method = RequestMethod.GET)
    public List<Products> findAllProduct() {
        List<Products> result = productRepository.findAll();
//        System.out.println(result.size());
        return result;
    }

    @JsonView(View.ProductView.class)
    @RequestMapping(value = "addProduct", method = RequestMethod.POST)
    public List<Products> addNewProduct(String productName, int quantity, double price) {
        Products product = new Products();
        product.setProductName(productName);
        product.setProductQuantity(quantity);
        product.setProductPrice(price);
        product.setStatus(1);
        productRepository.save(product);
        return findAllProduct();
    }

    @JsonView(View.ProductView.class)
    @RequestMapping(value = "updateProduct", method = RequestMethod.POST)
    public List<Products> updateProduct(int id, String productName, int quantity, double price, int status) {
        Products product = productRepository.findOne(id);
        product.setProductName(productName);
        product.setProductQuantity(quantity);
        product.setProductPrice(price);
        product.setStatus(status);
        productRepository.save(product);
        return findAllProduct();
    }

    @JsonView(View.ProductView.class)
    @Transactional
    @RequestMapping(value = "removeProduct", method = RequestMethod.POST)
    public List<Products> removeProduct(int id) {
        Products product = productRepository.findOne(id);
        product.setStatus(0);
        productRepository.save(product);
        return findAllProduct();
    }

    @JsonView(View.ProductView.class)
    @RequestMapping(value = "searchProductByName", method = RequestMethod.POST)
    public List<Products> searchProductByName(String searchValue) {
        List<Products> productList = new ArrayList<>();
        return productList;
    }

    @JsonView({View.ProductView.class})
    @RequestMapping(value = "findProductById", method = RequestMethod.POST)
    public Products findProductById(int id) {
        return productRepository.findOne(id);
    }
}
