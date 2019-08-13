package edu.mum.cs.clientservice.controller;

import edu.mum.cs.clientservice.adminService.AdminService;
import edu.mum.cs.clientservice.adminmodel.Address;
import edu.mum.cs.clientservice.adminmodel.Role;
import edu.mum.cs.clientservice.adminmodel.Status;
import edu.mum.cs.clientservice.adminmodel.User;
import edu.mum.cs.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class AccountController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/account/profile")
    public String getProfile() {
        return "user_profile";
    }

    @GetMapping("/login")
    public String login(@RequestParam Map<String,String> map, HttpSession session){
        if(map.get("email")!=null){
          User user= clientService.login(map.get("email"));
          //
        }
        return"shop";
    }

    @PostMapping("/registration")
    public @ResponseBody User createUser(@RequestParam Map<String, String> map) {
        System.out.println("Test");
        System.out.println(map.get("confirm"));
        System.out.println(map.get("password"));
        User user = new User();
        if (map.get("confirm").equals(map.get("password"))) {
            user.setName(map.get("name"));
            user.setLastName(map.get("lastName"));
            user.setEmail(map.get("email"));
            user.setPassword(map.get("password"));
            user.setActive(1);
            user.setRole(Role.BUYER);
            user.setAddress(null);
            user.setStatus(Status.APPROVED);
            clientService.createAccount(user);
        }
        else {
            System.out.println("Password must match!");
        }
        return user;
    }


}
