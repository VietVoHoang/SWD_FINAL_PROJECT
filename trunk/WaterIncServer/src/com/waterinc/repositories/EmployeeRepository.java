package com.waterinc.repositories;

import com.waterinc.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hongducphan on 7/3/17.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findAll();
}
