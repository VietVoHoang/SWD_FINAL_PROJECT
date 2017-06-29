package com.waterinc.repositories;

import com.waterinc.model.Agency;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Asus on 6/28/2017.
 */
public interface AgencyRepository extends CrudRepository<Agency, Integer> {
    List<Agency> findAll();
}
