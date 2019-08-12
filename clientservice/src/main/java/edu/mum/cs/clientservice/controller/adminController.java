package edu.mum.cs.clientservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminController {
    @GetMapping("/admin/login")
    public String adminLogin(){
        return "admin/admin_login";
    }
    @GetMapping("/admin/mainpanel")
    public String adminHome(){
        return "admin/admin_mainpage";
    }
}
