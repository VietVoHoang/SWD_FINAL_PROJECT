package com.waterinc.repositories;

import com.waterinc.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hongducphan on 7/3/17.
 */
public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findAll();
}
