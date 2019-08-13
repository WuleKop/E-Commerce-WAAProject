package edu.mum.cs.clientservice.controller;

import edu.mum.cs.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private ClientService clientService;
    @GetMapping("/logon")
    public String login(@RequestParam Map<String,String> map){
        return "customer-login";
    }
}
