package com.example.sweater;

import com.example.sweater.domain.MyUser;
import com.example.sweater.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private UsersRepo usersRepo;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String users(Map<String, Object> model) {
        Iterable<MyUser> users = usersRepo.findAll();

        model.put("users", users);

        return "users";
    }

    @PostMapping
    public String add(@RequestParam String name,
                      @RequestParam String last_name,
                      @RequestParam String login,
                      @RequestParam String password, Map<String, Object> model) {
        MyUser myUser = new MyUser(name, last_name, login, password);
        usersRepo.save(myUser);

        Iterable<MyUser> users = usersRepo.findAll();

        model.put("users", users);

        return "users";
    }
}
