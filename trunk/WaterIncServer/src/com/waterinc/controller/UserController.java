package com.waterinc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.model.User;
import com.waterinc.repositories.UserRepository;
import com.waterinc.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by hongducphan on 7/3/17.
 */
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<User> getAllUser() {
        List<User> result = userRepository.findAll();
        System.out.println(result.size());
        return result;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public User addUser(String userName, String password, int enable, String role) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setEnable(enable);
        user.setRole(role);
        return userRepository.save(user);
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public User updateUser(int id, String userName, String password, int enable, String role) {
        User user = userRepository.findOne(id);
        user.setUsername(userName);
        user.setPassword(password);
        user.setEnable(enable);
        user.setRole(role);
        return userRepository.save(user);
    }

    @RequestMapping(value = "removeUser", method = RequestMethod.POST)
    public void removeUser(int id) {
        User user = userRepository.findOne(id);
        if(user != null) {
            user.setEnable(0);
        }
        userRepository.save(user);
    }
}
