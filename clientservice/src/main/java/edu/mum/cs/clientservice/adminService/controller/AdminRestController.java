package edu.mum.cs.clientservice.adminService.controller;


import edu.mum.cs.clientservice.adminService.AdminService;
import edu.mum.cs.clientservice.adminmodel.Address;
import edu.mum.cs.clientservice.adminmodel.Role;
import edu.mum.cs.clientservice.adminmodel.Status;
import edu.mum.cs.clientservice.adminmodel.User;
import edu.mum.cs.clientservice.utility.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/createAccountSeller")
    public User createAccountSeller(@RequestParam Map<String, String> map) {
        if (map.get("password").equals(map.get("confirm"))) {
            User user = new User();
            user.setEmail(map.get("email"));
            user.setLastName(map.get("lastName"));
            user.setName(map.get("firstName"));
            user.setPassword(MessageConverter.getMd5(map.get("password")));
            user.setStatus(Status.PENDING);
            user.setRole(Role.SELLER);
            user.setActive(1);

            Address address = new Address();
            address.setCity(map.get("city"));
            address.setCountry(map.get("country"));
            address.setState(map.get("state"));
            address.setStreet(map.get("street"));

            user.setAddress(address);
            User res = adminService.createAccountForSeller(user);

           return  res;
        } else {
            return null;
        }
    }


}
