package com.example.sweater.controller;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/users_select")
    public String users_select(Map<String, Object> model) {
        Iterable<User> users = userRepo.findAll();
        model.put("users", users);
        return "users_select";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
       User userFromDb =  userRepo.findByUsername(user.getUsername());
       if(userFromDb != null) {
            model.put("message", "Такой пользователь уже существует!");
           Iterable<User> users = userRepo.findAll();
           model.put("users", users);
            return "registration";
       }
       user.setActive(true);
       user.setRoles(Collections.singleton(Role.USER));
       userRepo.save(user);
       return "redirect:/";
    }

    @PostMapping("/add_user")
    public String add(User user, Map<String, Object> model) {

        User userFromDb =  userRepo.findByUsername(user.getUsername());
        if(userFromDb != null) {
            model.put("message", "Такой пользователь уже существует!");
            Iterable<User> users = userRepo.findAll();
            model.put("users", users);

            return "users_select";
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        Iterable<User> users = userRepo.findAll();

        model.put("users", users);

        return "users_select";
    }
}
