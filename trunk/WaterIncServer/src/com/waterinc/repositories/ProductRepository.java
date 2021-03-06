package com.waterinc.repositories;

import com.waterinc.model.Products;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Asus on 6/29/2017.
 */
public interface ProductRepository extends CrudRepository<Products, Integer> {
    List<Products> findAll();

}
