package edu.mum.cs.clientservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/account/profile")
    public String getProfile(){
        return "user_profile";
    }


}
