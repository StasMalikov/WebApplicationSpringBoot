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
public class MainController {

    @Autowired
    private UsersRepo usersRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/users")
    public String users(Map<String, Object> model) {
        Iterable<MyUser> users = usersRepo.findAll();

        model.put("users", users);

        return "users";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam String last_name,
                         @RequestParam String login, @RequestParam String password, Map<String, Object> model) {

        MyUser myUser = new MyUser(name, last_name, login, password);
        usersRepo.save(myUser);

        Iterable<MyUser> users = usersRepo.findAll();

        model.put("users", users);

        return "users";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam String name, @RequestParam String last_name,
                      @RequestParam String login, @RequestParam String password,
                      @RequestParam String submit, @RequestParam String id, Map<String, Object> model) {
         if (submit.equals("Изменить")) {
            MyUser user = usersRepo.findById(new Integer(id)).get(0);
            user.setName(name);
            user.setLast_name(last_name);
            user.setLogin(login);
            user.setPassword(password);
            usersRepo.save(user);
        } else if (submit.equals("Удалить")) {
            usersRepo.delete(usersRepo.findById(new Integer(id)).get(0));
        }

        Iterable<MyUser> users = usersRepo.findAll();

        model.put("users", users);

        return "users";
    }
}
