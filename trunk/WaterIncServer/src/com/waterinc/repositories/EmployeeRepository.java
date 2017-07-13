package com.waterinc.repositories;

import com.waterinc.model.Employees;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hongducphan on 7/3/17.
 */
public interface EmployeeRepository extends CrudRepository<Employees, Integer> {
    List<Employees> findAll();
}
