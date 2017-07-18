package com.waterinc.repositories;

import com.waterinc.model.Orderitems;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Asus on 7/14/2017.
 */
public interface OrderItemRepository extends CrudRepository<Orderitems, Integer> {
    List<Orderitems> findAllByOrderId(int id);

    @Query("SELECT o FROM Orderitems o WHERE o.orderId = :orderId")
    List<Orderitems> findAllByOrderIdTest(@Param("orderId") int orderId);
}
