package com.waterinc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.waterinc.config.SecurityUserDetailService;
import com.waterinc.model.Users;
import com.waterinc.repositories.UserRepository;
import com.waterinc.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by hongducphan on 7/3/17.
 */
@RestController
@EnableWebMvc
public class UserController {
    @Autowired
    UserRepository userRepository;

    @JsonView({View.UserView.class})
    @RequestMapping(value = "findAllUser", method = RequestMethod.GET)
    public List<Users> getAllUser() {
        List<Users> result = userRepository.findAll();
        System.out.println(result.size());
        return result;
    }

    @JsonView({View.UserView.class})
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public List<Users> addUser(String username, String password, int enable, String role, int empId) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setEnable(enable);
        user.setRole(role);
        user.setEmployeeId(empId);
        userRepository.save(user);
        return getAllUser();
    }

    @JsonView({View.UserView.class})
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public List<Users> updateUser(int id, String userName, String password, int enable, String role, int empId) {
        Users user = userRepository.findOne(id);
        user.setUsername(userName);
        user.setPassword(password);
        user.setEnable(enable);
        user.setRole(role);
        user.setEmployeeId(empId);
        userRepository.save(user);
        return getAllUser();
    }

    @JsonView({View.UserView.class})
    @RequestMapping(value = "removeUser", method = RequestMethod.POST)
    public List<Users> removeUser(int id) {
        Users user = userRepository.findOne(id);
        if (user != null) {
            user.setEnable(0);
        }
        userRepository.save(user);
        return getAllUser();
    }

    @JsonView({View.UserView.class})
    @RequestMapping(value = "findUserById", method = RequestMethod.POST)
    public Users findUserById(int id) {
        return userRepository.findOne(id);
    }

    @JsonView({View.UserView.class})
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Users login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login.jsp";
    }

    @JsonView(View.UserView.class)
    @RequestMapping(value = "findUserByUsername", method = RequestMethod.GET)
    public Users finUserByUsername(){
        String uName = SecurityUserDetailService.uName;
//        System.out.println(uName);
        return userRepository.findByUsername(uName);
    }
}
