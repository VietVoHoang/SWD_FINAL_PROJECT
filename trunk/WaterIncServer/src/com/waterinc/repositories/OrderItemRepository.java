package com.waterinc.repositories;

import com.waterinc.model.Orderitems;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Asus on 7/14/2017.
 */
public interface OrderItemRepository extends CrudRepository<Orderitems, Integer> {
    List<Orderitems> findAllByOrderId(int id);
}
