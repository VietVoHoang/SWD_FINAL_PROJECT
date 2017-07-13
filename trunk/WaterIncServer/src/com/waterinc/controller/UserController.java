package com.waterinc.controller;

import com.waterinc.model.Users;
import com.waterinc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

/**
 * Created by hongducphan on 7/3/17.
 */
@RestController
@EnableWebMvc
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "findAllUser", method = RequestMethod.GET)
    public List<Users> getAllUser() {
        List<Users> result = userRepository.findAll();
        System.out.println(result.size());
        return result;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public Users addUser(String userName, String password, int enable, String role) {
        Users user = new Users();
        user.setUsername(userName);
        user.setPassword(password);
        user.setEnable(enable);
        user.setRole(role);
        return userRepository.save(user);
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public Users updateUser(int id, String userName, String password, int enable, String role) {
        Users user = userRepository.findOne(id);
        user.setUsername(userName);
        user.setPassword(password);
        user.setEnable(enable);
        user.setRole(role);
        return userRepository.save(user);
    }

    @RequestMapping(value = "removeUser", method = RequestMethod.POST)
    public void removeUser(int id) {
        Users user = userRepository.findOne(id);
        if(user != null) {
            user.setEnable(0);
        }
        userRepository.save(user);
    }
}
