package com.waterinc.repositories;

import com.waterinc.model.Salary;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hongducphan on 7/16/17.
 */
public interface SalaryRepository extends CrudRepository<Salary, Integer> {
    List<Salary> findAll();
}
