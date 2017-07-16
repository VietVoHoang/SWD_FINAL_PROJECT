package com.waterinc.repositories;

import com.waterinc.model.Bonuscoefficient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hongducphan on 7/16/17.
 */
public interface BonuscoefficientRepository extends CrudRepository<Bonuscoefficient, Integer> {
    List<Bonuscoefficient> findAll();
}
