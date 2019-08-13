package edu.mum.cs.clientservice.controller;


import edu.mum.cs.clientservice.adminmodel.User;
import edu.mum.cs.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class AccountRestContoller {

    @Autowired
    private ClientService clientService;

    @PostMapping("login")
    public String login(@RequestParam Map<String, String> map, HttpSession session) {
        System.out.println("lets test");
        User user = clientService.login(map.get("email"));
        if(user  != null) {
            if (map.get("password").equals(user.getPassword())) {
                session.setAttribute("user", user);
                return "user found";
            } else {
                session.invalidate();
                return "Invalid Password";
            }
        }
        return "Invalid Password";

    }
}
