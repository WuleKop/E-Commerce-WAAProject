package edu.mum.cs.clientservice.controller;

import edu.mum.cs.clientservice.adminService.AdminService;
import edu.mum.cs.clientservice.adminmodel.Address;
import edu.mum.cs.clientservice.adminmodel.Role;
import edu.mum.cs.clientservice.adminmodel.Status;
import edu.mum.cs.clientservice.adminmodel.User;
import edu.mum.cs.clientservice.buyerservice.BuyerService;
import edu.mum.cs.clientservice.service.ClientService;
import edu.mum.cs.clientservice.utility.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import sun.security.provider.MD5;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class AccountController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private BuyerService buyerService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/account/profile")
    public String getProfile() {
        return "user_profile";
    }


    @PostMapping("/registration")
    public @ResponseBody
    User createUser(@RequestParam Map<String, String> map) {
        User user = new User();
        if (map.get("confirm").equals(map.get("password"))) {
            user.setName(map.get("firstName"));
            user.setLastName(map.get("lastName"));
            user.setEmail(map.get("email"));
            user.setPassword(MessageConverter.getMd5(map.get("password")));
            user.setActive(1);
            user.setRole(Role.BUYER);
            user.setAddress(null);
            user.setStatus(Status.APPROVED);
            User savedUser = clientService.createAccount(user);
            return savedUser;
        } else {
            return null;
        }

    }


}
