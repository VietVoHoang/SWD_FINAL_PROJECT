package com.waterinc.repositories;

import com.waterinc.model.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Asus on 7/2/2017.
 */
public interface UserRepository extends CrudRepository<Users, Integer> {
    Users findByUsername(String username);

    List<Users> findAll();
}
