package edu.mum.cs.clientservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

    @GetMapping("/account/register")
    public String register(){
        return "customer-account";
    }

    @GetMapping("/account/profile")
    public String getProfile(){
        return "user_profile";
    }
}
